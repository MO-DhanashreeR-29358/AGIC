package com.mosl.mobile.volunteer.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.editorActions
import com.mosl.app.core.error.getErrorOnFailure
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.coreui.base.LeanBaseActivity
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.annotations.RemoteConfigData
import com.mosl.mobile.volunteer.appuser.model.RmUser
import com.mosl.mobile.volunteer.core.MOSLCommonActivity
import com.mosl.mobile.volunteer.data.RemoteConfigModel
import com.mosl.mobile.volunteer.databinding.ActivityLoginBinding
import com.mosl.mobile.volunteer.databinding.AlertDialogBinding
import com.mosl.navigator.AppTitle
import com.mosl.navigator.AuthorizationState
import com.mosl.navigator.FragmentNavigationEntry
import com.mosl.navigator.Navigator
import com.mosl.shared.util.getDeviceID
import io.reactivex.Observable
import javax.inject.Inject

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LoginActivity : LeanBaseActivity(), Navigator, LoginView {

    lateinit var binding: ActivityLoginBinding
    @Inject
    lateinit var presenter: LoginPresenter

    @Inject
    @field:RemoteConfigData
    lateinit var remoteConfigData: StringPreference
    @Inject
    lateinit var gson: Gson

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityLoginBinding.bind(findViewById(R.id.login_layout))
        presenter.start()
        initData()
    }

    private fun initData() {
        val banner: RemoteConfigModel? =
            gson.fromJson(remoteConfigData.get(), RemoteConfigModel::class.java)
        Glide.with(this)
            .load(banner?.logo)
            .into(binding.imgBanner)
    }

    internal fun openDashboard() {
        val intent = Intent(this, MOSLCommonActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun toggleProgressBar(inProgress: Boolean) {
        if (inProgress) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    override fun onLoginClicked(): Observable<Unit> {
        return binding.txtLogin.clicks()
    }

    override fun onDoneClicked(): Observable<Int> {
        return binding.etUserId.editorActions { event -> event == EditorInfo.IME_ACTION_DONE }
    }

    override fun renderLogin(uiModel: UiModel<RmUser>) {
        toggleProgressBar(uiModel.inProgress)

        uiModel.onFailure { error ->
            error.showOnFailurePopup()
        }

        uiModel.onSuccess {
            openDashboard()
        }
    }

    private fun Throwable.showOnFailurePopup() {
        val errorOnFailure = this.getErrorOnFailure()
        alertMessage(message = errorOnFailure.getResolvedMessage(applicationContext))
    }

    private lateinit var bindingDialog: AlertDialogBinding

    private fun alertMessage(message: String) {

        //Inflate the dialog with custom view
        val dialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null)

        bindingDialog = AlertDialogBinding.bind(dialogView)


        val mBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
        val mAlertDialog = mBuilder.show()
        bindingDialog.txtError.text = message

        //cancel button click of custom layout
        bindingDialog.btnOk.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }

    override fun getUserId(): String {
        return binding.etUserId.text?.trim()
            .toString()
    }

    override fun getDeviceId(): String {
        return getDeviceID(applicationContext.contentResolver)
    }

    override fun isDataEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataAction(): Observable<DataAction> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun authorizationDialog(listener: (AuthorizationState) -> Unit) {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearTitles() {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContainerViewId(): Int {
        return 0
    }

    override fun getFragment(pageId: Int): Fragment? {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun gotoPage(fragmentEntry: FragmentNavigationEntry) {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideAppTitle() {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun setAppTitle(
        page: String,
        appTitle: AppTitle?
    ) {
        TODO(
            "not implemented"
        ) //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }


}
