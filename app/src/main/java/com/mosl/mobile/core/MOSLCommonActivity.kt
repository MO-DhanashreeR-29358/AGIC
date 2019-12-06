package com.mosl.mobile.core

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.jakewharton.rxbinding3.appcompat.navigationClicks
import com.mosl.app.core.network.NetworkChangeEvent
import com.mosl.app.core.network.NetworkReceiver
import com.mosl.app.core.utils.hideKeyBoard
import com.mosl.app.coreui.base.ActivityDelegate
import com.mosl.app.coreui.base.BaseActivity
import com.mosl.app.coreui.base.NavigationElement
import com.mosl.mobile.R
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.contactrm.ContactDialogFragment
import com.mosl.mobile.coreui.LocaleManager
import com.mosl.mobile.dashboard.ActivityNavigationHelper
import com.mosl.mobile.databinding.ActivityCommonbaseBinding
import com.mosl.mobile.domain.schedule.ScheduleRepository
import com.mosl.mobile.login.LoginActivity
import com.mosl.mobile.login.UserCacheImpl
import com.mosl.mobile.menu.ScreenNavigatorImpl
import com.mosl.mobile.pdf.PdfFragment
import com.mosl.navigator.*
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
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
    lateinit var repository: ScheduleRepository

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
            activityNavHelper.loadIntent(intent)
        } else {
            globalTitles =
                savedInstanceState.getSerializable(PAGE_TITLES) as HashMap<String, AppTitle>
        }

        setupObservables()
        setUpView()
        setUpTabs()

    }


    private fun setUpView() {

        binding.txtFundName.setText(userManager.user.fundName)
        binding.txtName.setText(userManager.user.clientName)
        binding.txtFundName.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        binding.txtName.setEllipsize(TextUtils.TruncateAt.MARQUEE)
        binding.txtFundName.isSelected = true
        binding.txtName.isSelected = true

        if (userManager.user.contactList().isEmpty()) {
            binding.imgCall.visibility = View.GONE
        } else {
            binding.imgCall.visibility = View.VISIBLE
        }

        binding.imgLogout.setOnClickListener {
            logoutCustomer()
        }

        binding.imgCall.setOnClickListener {
            val ft = getSupportFragmentManager().beginTransaction()
            ContactDialogFragment.newInstance(userManager.user.contactList())
                .show(ft, "dialog")
        }
    }


    var pdfLink: String? = null

    private fun setUpTabs() {
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText(R.string.tab_label_schedule).setIcon(R.drawable.ic_tab_schedule).setTag(
                NavigationConstants.NAVIGATE_TO_SCHEDULE
            )
        )

        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText(R.string.tab_label_overview).setIcon(R.drawable.ic_tab_overview).setTag(
                NavigationConstants.NAVIGATE_TO_OVERVIEW
            )
        )

        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText(R.string.tab_label_panel).setIcon(R.drawable.navigation_icon).setTag(
                NavigationConstants.NAVIGATE_TO_PANEL
            )
        )

        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText(R.string.tab_label_bytes).setIcon(R.drawable.ic_tab_bytes).setTag(
                NavigationConstants.NAVIGATE_TO_BYTES
            )
        )

        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText(R.string.tab_label_pdf).setIcon(R.drawable.ic_tab_pdf).setTag(
                NavigationConstants.NAVIGATE_TO_PDF
            )
        )

        /* binding.tabLayout.addTab(
             binding.tabLayout.newTab().setText(R.string.tab_label_notes).setIcon(R.drawable.ic_tab_notes).setTag(
                 NavigationConstants.NAVIGATE_TO_NOTES
             )
         )*/


        setColorFilter(binding.tabLayout.getTabAt(0)?.icon, baseContext.setColor(R.color.blue))



        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                setColorFilter(tab.icon, baseContext.setColor(R.color.blue))
                if (tab.tag as Int == NavigationConstants.NAVIGATE_TO_PDF) {
                    val ft = getSupportFragmentManager().beginTransaction()
                    if (!pdfLink.isNullOrEmpty()) {
                        pdfLink?.let {
                            PdfFragment.newInstance(it)
                                .show(ft, "")
                        }
                    }

                } else {
                    navigateFragment(tab.tag as Int)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                setColorFilter(tab.icon, baseContext.setColor(R.color.white))
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    private fun Context.setColor(color: Int): Int {
        return ContextCompat.getColor(this, color)
    }

    private fun navigateFragment(pageId: Int) {
        val bundle = Bundle()
        when (pageId) {
        }

        navigateTo(pageId, bundle, true)
    }

    private fun setColorFilter(drawable: Drawable?, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable?.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP) as ColorFilter?
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

    override fun hideAppTitle() {
    }

    override fun hideProgress() {
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

    internal fun logoutCustomer() {
        AlertDialog.Builder(this)
            .setMessage(R.string.msg_logout)
            .setCancelable(false)
            .setPositiveButton(R.string.text_label_yes) { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                userCacheImpl.clearUser()
                finish()
            }
            .setNegativeButton(R.string.text_label_no) { _, _ -> }
            .show()
    }


    override fun setAppTitle(page: String, appTitle: AppTitle?) {
        val title = appTitle ?: globalTitles[page] ?: AppTitle.EMPTY
        Timber.d("ActionBarNew Set Title for %s, to %s", page, title)
        globalTitles[page] = title
        binding.toolBar.title = title.toString()
    }

    override fun showProgress() {
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
        const val IS_FIRST_TIME = "isFirstTime"
        private const val PAGE_TITLES = "pageTitles"
    }

}