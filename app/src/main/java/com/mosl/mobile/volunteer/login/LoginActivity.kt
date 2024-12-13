package com.mosl.mobile.volunteer.login

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.editorActions
import com.mosl.annotations.FcmToken
import com.mosl.app.core.data.fcm.FcmTokenSyncHelper
import com.mosl.app.core.error.getErrorOnFailure
import com.mosl.app.core.events.DataAction
//import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.core.rx.applyUiModel
import com.mosl.app.core.utils.getUuid
import com.mosl.app.coreui.base.LeanBaseActivity
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.commons.date.CalendarUtils
import com.mosl.data.api.fcmtoken.FcmTokenRequest
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.annotations.*
import com.mosl.mobile.volunteer.appuser.login.LoginRepositoryImpl
import com.mosl.mobile.volunteer.appuser.model.Volunteer
import com.mosl.mobile.volunteer.core.MOSLCommonActivity
import com.mosl.mobile.volunteer.data.RemoteConfigModel
import com.mosl.mobile.volunteer.databinding.ActivityLoginBinding
import com.mosl.mobile.volunteer.databinding.AlertDialogBinding
import com.mosl.navigator.AppTitle
import com.mosl.navigator.AuthorizationState
import com.mosl.navigator.FragmentNavigationEntry
import com.mosl.navigator.Navigator
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.EMPTY_STRING
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import mosl.rx_preferences.Preference
import com.mosl.app.core.prefs.IntegerPreference
import com.mosl.app.core.prefs.StringPreference


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

    @Inject
    @field:LastScheduleDate
    lateinit var lastScheduleDate: StringPreference

    @Inject
    @field:ImageSplashUrl
    lateinit var imageSplashUrl: StringPreference

    @Inject
    @field:LastBytesDate
    lateinit var lastBytesDate: StringPreference

    @Inject
    @field:ScheduleData
    lateinit var scheduleData: StringPreference

    @Inject
    @field:PanelData
    lateinit var panelData: StringPreference

    @Inject
    @field:OverViewData
    lateinit var overViewData: StringPreference

    @Inject
    @field:CorporateScheduleData
    lateinit var corporateScheduleData: StringPreference

    @Inject
    @field:LastCorporateScheduleDate
    lateinit var lastCorporateScheduleDate: StringPreference

    @Inject
    @field:LastNotificationDate
    lateinit var lastNotificationDate: StringPreference

    @Inject
    @field:NotificationData
    lateinit var notificationDate: StringPreference

    @Inject
    @field:NotificationCount
    lateinit var notificationCount: IntegerPreference

    @Inject
    @field:FcmToken
    lateinit var tokenPref: Preference<String>

    @Inject
    lateinit var loginRepository: LoginRepositoryImpl

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
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.imgBanner)
    }

    internal fun openDashboard() {
        val intent = Intent(this, MOSLCommonActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun doLogout() {
        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        notificationManager.cancelAll()
        val uuid = this.getUuid()
        val tokenRequest = FcmTokenRequest(
            tokenPref.get(),
            uuid,
            FcmTokenSyncHelper.PLATFORM,
            "13708"
        )
        disposable.add(loginRepository.logout(tokenRequest)
            .toSingleDefault(true).toObservable()
            .compose(applyUiModel())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { uiModel ->
                toggleProgressBar(uiModel.inProgress)

                uiModel.onSuccess {
                    preferenceClear()
                }

                uiModel.onFailure { error ->
                    error.showOnFailurePopup()
                }
            })
    }

    private fun preferenceClear() {
        lastScheduleDate.set("")
        imageSplashUrl.set("")
        lastBytesDate.set("")
        scheduleData.set("")
        panelData.set("")
        overViewData.set("")
        corporateScheduleData.set("")
        lastCorporateScheduleDate.set("")
        lastNotificationDate.set("")
        notificationDate.set("")
        notificationCount.set(0)
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

    override fun renderLogin(uiModel: UiModel<Volunteer>) {
        toggleProgressBar(uiModel.inProgress)

        uiModel.onFailure { error ->
            error.showOnFailurePopup()
        }

        uiModel.onSuccess {
            if (it.corporateList.isEmpty() && it.message.isNullOrEmpty().not()){
                alertMessage(it.message)
            }else {
                openDashboard()
            }
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
            doLogout()
            mAlertDialog.dismiss()
        }
    }

    override fun getUserId(): String {
        return binding.etUserId.text?.trim()
            .toString()
    }

    override fun getTodaysDate(): String {
        return EMPTY_STRING
    }

    override fun getPassword(): String {
        return binding.etPassword.text?.trim().toString()
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

    override fun getFragment(pageId: String): Fragment? {
        TODO("Not yet implemented")
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

    override fun isConnected(): Boolean {
        return true
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
