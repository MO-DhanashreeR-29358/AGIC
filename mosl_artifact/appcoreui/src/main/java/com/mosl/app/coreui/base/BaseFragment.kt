package com.mosl.app.coreui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import com.mosl.app.core.error.ErrorModel
import com.mosl.app.core.error.getErrorOnFailure
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.network.NetworkChangeEvent
import com.mosl.app.coreui.R
import com.mosl.app.coreui.dialog.*
import com.mosl.app.coreui.views.BlockingProgressLayout
import com.mosl.commons.rx.Dispatchers
import com.mosl.navigator.AppTitle
import com.mosl.navigator.Navigator
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment : LeanBaseFragment(), NavigationElement {
  @JvmField
  protected val dataActionSubject: PublishSubject<DataAction> =
    PublishSubject.create<DataAction>()

  @JvmField
  protected val onRetryClick = OnClickListener {
    if (navigator.isConnected()) {
      sendServiceRequest()
    } else {
      toast(getString(R.string.check_connection))
    }
  }

  @Inject
  lateinit var dispatchers: Dispatchers

  @Inject
  lateinit var activityDelegate: ActivityDelegate
  private var isNetworkConnected = true
  private var mFragmentResult: Bundle? = null
  private lateinit var navigator: Navigator
  private var backPressEnabled = true

  override fun onAttach(context: Context) {
    this.navigator = context as Navigator
    super.onAttach(context)
  }

  override fun getLayout(): Int {
    return R.layout.fragment_future_page
  }

  @CallSuper
  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    isNetworkConnected = navigator.isConnected()
    super.onViewCreated(view, savedInstanceState)
    extractArguments(arguments ?: Bundle.EMPTY)
    setMenuSelection()
  }

  open fun extractArguments(bundle: Bundle) {}

  fun getNavigator(): Navigator {
    return navigator
  }

  override fun setFragmentResult(result: Bundle) {
    setResult(result)
  }

  private fun setResult(result: Bundle) {
    this.mFragmentResult = result
  }

  override fun onBackPressed(): Boolean {
    return false
  }

  fun setBackPressEnabled(enabled: Boolean) {
    backPressEnabled = enabled
  }

  override fun isBackPressEnabled(): Boolean {
    return backPressEnabled
  }

  override fun onFragmentReopened(args: Bundle) {
    Timber.d("Page Reopened With %s", args)
  }

  override fun networkChanged(info: NetworkChangeEvent) {
    val wasConnected = isNetworkConnected
    val networkAvailable = info.isConnected
    isNetworkConnected = networkAvailable
    if (!wasConnected && networkAvailable && isVisible) {
      sendServiceRequest()
    }
  }

  @CallSuper
  override fun onFragmentResume() {
    dispatchOnFragmentResult()
    setMenuSelection()
  }

  private fun dispatchOnFragmentResult() {
    mFragmentResult?.let {
      val resultBundle = Bundle(it)
      onFragmentResult(resultBundle)
      mFragmentResult = null
    }
  }

  override fun onFragmentPause() {
    Timber.i(
      "onFragmentPause Page : %s, Added : %s, Visible : %s, isHidden : %s, Detached : %s",
      this, isAdded, isVisible, isHidden, isDetached
    )
  }

  open fun sendServiceRequest() {
    dataActionSubject.onNext(DataAction.Retry)
  }

  open fun isDataEmpty(): Boolean {
    return false
  }

  /**
   * Set title on the title bar.
   *
   * @param title the title to be set
   */
  protected fun setAppTitle(title: AppTitle) {
    if (isVisible) navigator.setAppTitle(this.toString(), title)
  }

  protected fun setAppTitle(@StringRes pageTitle: Int) {
    setAppTitle(AppTitle.withTitle(pageTitle))
  }

  protected fun setAppTitle(pageTitle: String) {
    setAppTitle(AppTitle.withTitle(pageTitle))
  }

  /**
   * Hides the title bar.
   */
  protected fun hideAppTitle() {
    navigator.hideAppTitle()
  }

  /**
   * Shows the universal loading screen.
   */
  open fun showProgress() {
    navigator.showProgress()
  }

  /**
   * Hides the universal loading screen.
   */
  open fun hideProgress() {
    navigator.hideProgress()
  }

  open fun toggleProgressBar(inProgress: Boolean) {
    if (inProgress) {
      showProgress()
    } else {
      hideProgress()
    }
  }

  open fun BlockingProgressLayout.toggleProgressBar(inProgress: Boolean) {
    visibility = if (inProgress) {
      View.VISIBLE
    } else {
      View.GONE
    }
  }

  private fun setMenuSelection() {
    val menuLabelRes = getAssociatedMenuLabel()
    if (menuLabelRes > 0) {
      activityDelegate.selectMenu(menuLabelRes)
    }
  }

  open fun getAssociatedMenuLabel(): Int {
    return -1
  }

  fun selectMenuForPage(@StringRes label: Int) {
    activityDelegate.selectMenu(label)
  }

  fun onDataAction(): Observable<DataAction> {
    return dataActionSubject
  }

  fun getErrorOnFailure(throwable: Throwable): ErrorModel {
    return throwable.getErrorOnFailure()
  }

  fun showOnFailurePopup(t: Throwable) {
    val onFailure = getErrorOnFailure(t)
    showMessagePopup(onFailure)
  }

  fun showMessagePopup(error: ErrorModel) {
    requireActivity().showDialogForError(message = error.getResolvedMessage(requireContext()))
  }

  @JvmOverloads
  fun alert(
    message: String,
    title: String = getString(R.string.alert_dialog_title)
  ) {
    return requireActivity().showDialogForSuccess(message, title)
  }

  fun toast(message: String, isBackPress: Boolean = false) {
    requireActivity().showToastForWarning(message = message, isBackPress = isBackPress)
  }

  @JvmOverloads
  fun actionableAlert(
    message: String,
    cancelable: Boolean = false,
    @StringRes buttonText: Int = R.string.button_label_ok,
    @StringRes negativeButtonText: Int? = null,
    callback: (Boolean) -> Unit
  ) {
    return requireActivity().showDialogForConfirmation(
      message = message,
      cancelable = cancelable,
      positiveButton = getString(buttonText),
      negativeButton = negativeButtonText?.let { getString(it) },
      action = callback
    )
  }

  fun showErrorToast(message: String) {
    requireActivity().showToastForError(message)
  }

  fun showSuccessToast(message: String) {
    requireActivity().showToastForSuccess(message)
  }
}