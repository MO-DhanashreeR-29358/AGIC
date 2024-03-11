package com.mosl.mobile.core

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.jakewharton.rxbinding3.appcompat.navigationClicks
import com.mosl.annotations.FcmToken
import com.mosl.app.core.data.fcm.FcmTokenSyncHelper
import com.mosl.app.core.error.getErrorOnFailure
import com.mosl.app.core.network.NetworkChangeEvent
import com.mosl.app.core.network.NetworkReceiver
import com.mosl.app.core.network.isNetworkConnected
import com.mosl.app.core.prefs.IntegerPreference
import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.core.rx.applyUiModel
import com.mosl.app.core.utils.getUuid
import com.mosl.app.core.utils.hide
import com.mosl.app.core.utils.hideKeyBoard
import com.mosl.app.core.utils.show
import com.mosl.app.coreui.base.ActivityDelegate
import com.mosl.app.coreui.base.BaseActivity
import com.mosl.app.coreui.base.NavigationElement
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.data.api.fcmtoken.FcmTokenRequest
import com.mosl.mobile.R
import com.mosl.mobile.annotations.*
import com.mosl.mobile.appuser.login.LoginRepositoryImpl
import com.mosl.mobile.appuser.login.UserManager
import com.mosl.mobile.appuser.login.user
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.contactrm.ContactDialogFragment
import com.mosl.mobile.coreui.LocaleManager
import com.mosl.mobile.dashboard.ActivityNavigationHelper
import com.mosl.mobile.data.RemoteConfigModel
import com.mosl.mobile.databinding.ActivityCommonbaseBinding
import com.mosl.mobile.databinding.AlertDialogBinding
import com.mosl.mobile.databinding.LogoutDialogBinding
import com.mosl.mobile.login.LoginActivity
import com.mosl.mobile.login.UserCacheImpl
import com.mosl.mobile.menu.ScreenNavigatorImpl
import com.mosl.mobile.pdf.PdfFragment
import com.mosl.navigator.*
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.getTodaysDate
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import mosl.rx_preferences.Preference
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MOSLCommonActivity : BaseActivity(), Navigator, ActivityDelegate {

    @Inject
    lateinit var fragmentResolver: FragmentResolver

    @Inject
    lateinit var networkReceiver: NetworkReceiver
    lateinit var binding: ActivityCommonbaseBinding
    internal var snackbar: Snackbar? = null
    private var globalTitles = HashMap<String, AppTitle>()
    private var doubleBackToExitPressedOnce = false

    @Inject
    lateinit var navigationHelper: NavigationHelper
    internal val currentFragment: Fragment?
        get() = navigationHelper.currentFragment

    @Inject
    lateinit var screenNavigator: ScreenNavigatorImpl

    @Inject
    lateinit var activityNavHelper: ActivityNavigationHelper

    @Inject
    lateinit var userCacheImpl: UserCacheImpl

    @Inject
    lateinit var userManager: UserManager

    @Inject
    @field:RemoteConfigData
    lateinit var remoteConfigData: StringPreference

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
    @field:LastBytesData
    lateinit var bytesData: StringPreference

    @Inject
    lateinit var gson: Gson
    var remoteConfigModel: RemoteConfigModel?=null

    @Inject
    @field:FcmToken
    lateinit var tokenPref: Preference<String>

    @Inject
    @field:FeedbackPendingStatus
    lateinit var feedbackPendingStatus: StringPreference

    @Inject
    lateinit var fcmTokenSyncHelper: FcmTokenSyncHelper

    @Inject
    lateinit var loginRepository: LoginRepositoryImpl

    lateinit var alertBindingDialog: AlertDialogBinding
    lateinit var bindingDialog: LogoutDialogBinding

    override fun getLayout(): Int {
        return R.layout.activity_commonbase
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.withLocale(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonbaseBinding.bind(findViewById(R.id.drawer_layout))

        registerReceiver(
            networkReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        binding.toolBar.navigationClicks().subscribe { openMenuDrawer() }.addTo(disposable)

        if (savedInstanceState == null) {
            activityNavHelper.loadFragment(NavigationConstants.NAVIGATE_TO_OVERVIEW)
        } else {
            globalTitles =
                savedInstanceState.getSerializable(PAGE_TITLES) as HashMap<String, AppTitle>
        }
        remoteConfigModel =
            gson.fromJson(remoteConfigData.get(), RemoteConfigModel::class.java)
        setupObservables()
        setUpView()
        setUpTabs(intent)
        changeImage()

    }


    private fun setUpView() {

//        binding.txtFundName.setText(userManager.user.fundName)
        binding.txtName.setText(userManager.user.name)
//        binding.txtFundName.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        binding.txtName.setEllipsize(TextUtils.TruncateAt.MARQUEE)
//        binding.txtFundName.isSelected = true
        binding.txtName.isSelected = true

        /*if (userManager.user.contactList().isEmpty()) {
            binding.imgCall.visibility = View.GONE
        } else {
            binding.imgCall.visibility = View.VISIBLE
        }*/

        binding.imgLogout.setOnClickListener {
            logoutCustomer()
        }

        /*binding.imgCall.setOnClickListener {
            ContactDialogFragment.newInstance(userManager.user.contactList())
                .show(supportFragmentManager, "dialog")
        }*/
    }

    fun changeImage() {

        Glide.with(this@MOSLCommonActivity)
            .load(remoteConfigModel?.logo)
            .into(binding.imgLogo)

        Glide.with(this@MOSLCommonActivity)
            .load(remoteConfigModel?.conferenceLogo)
            .into(binding.imgLogoTemp)

        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                if (isNetworkConnected()) {
                    ImageViewAnimatedChange()
                }
                handler.postDelayed(this, 20000)
            }
        }
        handler.postDelayed(runnable, 5000)
    }


    fun ImageViewAnimatedChange() {

        val anim_out = AnimationUtils.loadAnimation(this@MOSLCommonActivity, R.anim.fade_out_image)
        val anim_in = AnimationUtils.loadAnimation(this@MOSLCommonActivity, R.anim.fade_in_image)

        anim_out.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                binding.imgLogo.visibility = View.GONE
                binding.imgLogoTemp.visibility = View.VISIBLE

                anim_in.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationRepeat(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
//                        binding.imgLogo.setImageResource(R.drawable.ideation_conference)
                        binding.imgLogo.visibility = View.VISIBLE
                        binding.imgLogoTemp.visibility = View.GONE
                    }
                })
                binding.imgLogo.startAnimation(anim_in)

            }
        })
        binding.imgLogo.startAnimation(anim_out)
    }


    private fun setUpTabs(intent: Intent) {
        binding.tabLayout.apply {
            addTab(
                newTab().setText(R.string.txt_label_dayWise)
                    .setIcon(R.drawable.ic_tab_schedule).setTag(
                        NavigationConstants.NAVIGATE_TO_OVERVIEW
                    )
            )
            addTab(
                newTab().setText(R.string.txt_label_clientWise)
                    .setIcon(R.drawable.ic_tab_schedule).setTag(
                        NavigationConstants.NAVIGATE_TO_RM_CLIENT_OVERVIEW
                    )
            )
            binding.tabLayout.addOnTabSelectedListener(tabSelectedListner)

//            screenNavigator.openPage(NavigationConstants.NAVIGATE_TO_OVERVIEW)

        }
    }


    internal val tabSelectedListner = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {

            setColorFilter(tab.icon, baseContext.setColor(R.color.blue))
            if (tab.tag as Int == NavigationConstants.NAVIGATE_TO_PDF) {
                val ft = getSupportFragmentManager().beginTransaction()
                if (userManager.getPdf().isNotEmpty()) {
                    PdfFragment.newInstance()
                        .show(ft, "")
                } else {
                    alertMessage(message = PDF_FILE_NOT_YET_UPLOADED)
                }
            } else {
                setColorFilter(tab.icon, baseContext.setColor(R.color.blue))
                navigateFragment(tab.tag as Int)
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
            setColorFilter(
                binding.tabLayout.getTabAt(tab.position)?.icon,
                baseContext.setColor(R.color.bottom_tab_white)
            )
        }

        override fun onTabReselected(tab: TabLayout.Tab) {
        }
    }

    private fun disableTab(tabLayout: TabLayout, index: Int) {
        (tabLayout.getChildAt(0) as ViewGroup).getChildAt(index).isEnabled = compareDate()
    }


    private fun compareDate(): Boolean {
        /*val currentDate = AppDateFormats.df_Date6.parse(getTodaysDate())
        val day = userManager.user.day.split(",")
        val loginDate = AppDateFormats.df_Date6.parse(day[0])
        currentDate?.apply {
            if (this.after(loginDate)) {
                return true
            }
        }*/
        return false
    }

    private fun setNotificationTab() {
       /* if (userManager.user.role.equals(ROLE_CLIENT, ignoreCase = true)) {
            setColorFilter(binding.tabLayout.getTabAt(3)?.icon, baseContext.setColor(R.color.blue))
            binding.tabLayout.getTabAt(3)?.select()
        } else if (userManager.user.role.equals(ROLE_CORPORATE, ignoreCase = true)) {
            setColorFilter(binding.tabLayout.getTabAt(2)?.icon, baseContext.setColor(R.color.blue))
            binding.tabLayout.getTabAt(2)?.select()
        }*/

    }

    private fun Intent.isNotification(): Boolean {
        val category: String? = getStringExtra(ActivityNavigationHelper.ARG_NOTIFICATION_CATEGORY)
        return category != null
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setNotificationTab()
    }

    fun setNotificationUpdate() {
        /*val count = notificationCount.get()
        if (count > 0) {
            if (userManager.user.role.equals(ROLE_CLIENT, ignoreCase = true)) {
                binding.tabLayout.getTabAt(3)?.orCreateBadge?.number = count

            } else if (userManager.user.role.equals(ROLE_CORPORATE, ignoreCase = true)) {
                binding.tabLayout.getTabAt(2)?.orCreateBadge?.number = count
            }
        } else {
            if (userManager.user.role.equals(ROLE_CLIENT, ignoreCase = true)) {
                binding.tabLayout.getTabAt(3)?.removeBadge()

            } else if (userManager.user.role.equals(ROLE_CORPORATE, ignoreCase = true)) {
                binding.tabLayout.getTabAt(2)?.removeBadge()
            }
        }*/
    }

    fun FeedbackBadgeVisiblity() {
        /*val status = if (feedbackPendingStatus.get().equals("true", ignoreCase = true)) {
            true
        } else {
            false
        }
        if (userManager.user.role.equals(ROLE_CLIENT, ignoreCase = true)) {
            if (status && compareDate()) {
                binding.tabLayout.getTabAt(2)?.orCreateBadge?.number = 1
            } else {
                binding.tabLayout.getTabAt(2)?.removeBadge()

            }
        } else if (userManager.user.role.equals(ROLE_CORPORATE, ignoreCase = true)) {
            if (status && compareDate()) {
                binding.tabLayout.getTabAt(1)?.orCreateBadge?.number = 1
            } else {
                binding.tabLayout.getTabAt(1)?.removeBadge()

            }
        }*/

    }

    private fun Context.setColor(color: Int): Int {
        return ContextCompat.getColor(this, color)
    }

    private fun navigateFragment(pageId: Int) {
        val bundle = Bundle()
        navigateTo(pageId, bundle, true)
    }

    private fun setColorFilter(drawable: Drawable?, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable?.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            drawable?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    fun navigateTo(
        id: Int,
        bundle: Bundle,
        addStack: Boolean
    ) {
        val activity = this
        if (activity != null) {
            hideKeyboard(activity.currentFocus)
            screenNavigator.openPage(id, bundle, addStack)
        }
    }

    protected fun hideKeyboard(view: View?) {
        if (view == null) return
        hideKeyBoard(view)
    }

    private fun openMenuDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun authorizationDialog(listener: (AuthorizationState) -> Unit) {
    }

    override fun clearTitles() {
    }

    override fun getContainerViewId(): Int {
        return R.id.activityFrameLayout
    }

    override fun getFragment(pageId: Int): Fragment? {
        return fragmentResolver.resolve(pageId)
    }

    override fun gotoPage(fragmentEntry: FragmentNavigationEntry) {
        fragTransitionHelper.navigateTo(fragmentEntry)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawers()
            return
        }

        if (binding.progressBar.visibility == View.VISIBLE) {
            binding.progressBar.visibility = View.GONE
            return
        }

        if (navigationHelper.onBackPressed()) {
            return
        }

        if (navigationHelper.canGoBack()) {
            screenNavigator.goBack()
            return
        }

        handleExitAppPopup()
    }

    private fun handleExitAppPopup() {

        exitPopup()

    }


    internal fun alertMessage(message: String) {

        //Inflate the dialog with custom view
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null)

        alertBindingDialog = AlertDialogBinding.bind(mDialogView)


        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        alertBindingDialog.txtError.text = message

        //cancel button click of custom layout
        alertBindingDialog.btnOk.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }

    internal fun logoutCustomer() {

        //Inflate the dialog with custom view
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.logout_dialog, null)

        bindingDialog = LogoutDialogBinding.bind(mDialogView)


        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()


        bindingDialog.btnLogout.setOnClickListener {
            doLogout()
            mAlertDialog.dismiss()
        }
        //cancel button click of custom layout
        bindingDialog.dialogCancelBtn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
    }

    private fun preferenceClear() {
        lastScheduleDate.set("")
        imageSplashUrl.set("")
        lastBytesDate.set("")
        scheduleData.set("")
        panelData.set("")
        overViewData.set("")
        bytesData.set("")
        corporateScheduleData.set("")
        feedbackPendingStatus.set("")
        lastCorporateScheduleDate.set("")
        lastNotificationDate.set("")
        notificationDate.set("")
        notificationCount.set(0)
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
            userManager.user.empCode
        )
        disposable.add(loginRepository.logout(tokenRequest)
            .toSingleDefault(true).toObservable()
            .compose(applyUiModel())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { uiModel ->
                toggleProgressBar(uiModel.inProgress)

                uiModel.onSuccess {
                    openLogin()
                    preferenceClear()
                }

                uiModel.onFailure { error ->
                    error.showOnFailurePopup()
                }
            })
    }

    fun Throwable.showOnFailurePopup() {
        val errorOnFailure = this.getErrorOnFailure()
        alertMessage(message = errorOnFailure.getResolvedMessage(applicationContext))
    }

    private fun openLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun toggleProgressBar(show: Boolean) {
        if (show) {
            showProgress()
        } else {
            hideProgress()
        }
    }

    override fun setAppTitle(page: String, appTitle: AppTitle?) {
        val title = appTitle ?: globalTitles[page] ?: AppTitle.EMPTY
        Timber.d("ActionBarNew Set Title for %s, to %s", page, title)
        globalTitles[page] = title
        binding.toolBar.title = title.toString()
    }

    override fun hideAppTitle() {
    }


    override fun hideProgress() {
        binding.progressBar.hide()
    }

    override fun showProgress() {
        binding.progressBar.show()
    }

    override fun hideOverflowMenu() {
    }

    override fun selectMenu(label: Int) {
    }

    override fun setOverflowClickListener(listener: OnClickListener?) {
    }

    override fun showOverflowMenu() {
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putSerializable(PAGE_TITLES, globalTitles)
    }


    private fun exitPopup() {
        if (doubleBackToExitPressedOnce) {
            finish()
            return
        }

        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_SHORT).show()

        disposable.add(Observable.timer(2000, TimeUnit.MILLISECONDS)
            .subscribe { doubleBackToExitPressedOnce = false })
    }

    internal fun setupObservables() {

//        disposable.add(
//
//            tokenPref.asObservable().distinctUntilChanged()
//                .map { _ -> tokenPref.get() }
//                .filter { token -> !token.equals("0") }
//                .switchMap { token ->
//                    fcmTokenSyncHelper.saveToken(token)
//                        .compose(applyUiModel())
//                }
//                .subscribe())

        val mainThread = dispatchers.main
        networkReceiver.onNetworkChanged()
            .distinctUntilChanged()
            .observeOn(mainThread)
            .subscribe(this::onEvent)
            .addTo(disposable)


        activityNavHelper.addTo(disposable)
    }


    fun onEvent(event: NetworkChangeEvent) {
        Timber.i("Network Changed : %s", event)

        val currentFragment = currentFragment
        if (currentFragment is NavigationElement) {
            (currentFragment as NavigationElement).networkChanged(event)
        }

        if (event.isConnected) {
            snackbar?.dismiss()
        } else {
            snackbar = showNetworkDisconnectedSnackbar()
        }
    }

    internal fun showNetworkDisconnectedSnackbar(): Snackbar {
        val snackbar = Snackbar.make(
            binding.coordinatorLayout, R.string.network_unavailable,
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction(R.string.button_label_ok) { _ -> snackbar.dismiss() }
        snackbar.show()
        return snackbar
    }

    override fun onDestroy() {
        super.onDestroy()
        globalTitles.clear()
        disposable.clear()
        unregisterReceiver(networkReceiver)
    }

    companion object {
        const val ARG_PAGE_ID = "pageId"
        private const val PAGE_TITLES = "pageTitles"
        const val ROLE_CORPORATE = "Corporate"
        const val ROLE_CLIENT = "Client"
        const val PDF_FILE_NOT_YET_UPLOADED = "PDF file Not yet uploaded"
    }

}