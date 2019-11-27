package com.mosl.mobile.core

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.jakewharton.rxbinding3.appcompat.navigationClicks
import com.mosl.app.core.network.NetworkReceiver
import com.mosl.app.core.utils.hideKeyBoard
import com.mosl.app.coreui.base.ActivityDelegate
import com.mosl.app.coreui.base.BaseActivity
import com.mosl.mobile.R
import com.mosl.mobile.coreui.LocaleManager
import com.mosl.mobile.dashboard.ActivityNavigationHelper
import com.mosl.mobile.databinding.ActivityCommonbaseBinding
import com.mosl.mobile.menu.ScreenNavigatorImpl
import com.mosl.mobile.nav.DebugConstants
import com.mosl.navigator.AppTitle
import com.mosl.navigator.AuthorizationState
import com.mosl.navigator.FragmentNavigationEntry
import com.mosl.navigator.FragmentResolver
import com.mosl.navigator.NavigationHelper
import com.mosl.navigator.Navigator
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import java.util.*
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

    override fun getLayout(): Int {
        return R.layout.activity_commonbase
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.withLocale(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommonbaseBinding.bind(findViewById(R.id.drawer_layout))
        binding.appBar.navigationClicks().subscribe { openMenuDrawer() }.addTo(disposable)
        if (savedInstanceState == null) {
            activityNavHelper.loadIntent(intent)
        } else {
            globalTitles =
                savedInstanceState.getSerializable(PAGE_TITLES) as HashMap<String, AppTitle>
        }

        setupTabs()
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

    override fun setAppTitle(page: String, appTitle: AppTitle?) {
        val title = appTitle ?: globalTitles[page] ?: AppTitle.EMPTY
        Timber.d("ActionBarNew Set Title for %s, to %s", page, title)
        globalTitles[page] = title
        binding.appBar.title = title.toString()
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

    override fun onDestroy() {
        super.onDestroy()
        globalTitles.clear()
        disposable.clear()
    }

    companion object {
        const val ARG_PAGE_ID = "pageId"
        const val IS_FIRST_TIME = "isFirstTime"
        private const val PAGE_TITLES = "pageTitles"
    }


    private fun setupTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.schedule).setIcon(R.drawable.navigation_icon).setTag(DebugConstants.NAVIGATE_TO_SCHEDULE))

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.overview).setIcon(R.drawable.navigation_icon).setTag(DebugConstants.NAVIGATE_TO_OVERVIEW))

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.panel).setIcon(R.drawable.navigation_icon).setTag(DebugConstants.NAVIGATE_TO_OVERVIEW))

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.bytes).setIcon(R.drawable.navigation_icon).setTag(DebugConstants.NAVIGATE_TO_LOGIN))

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.pdf).setIcon(R.drawable.navigation_icon).setTag(DebugConstants.NAVIGATE_TO_DASHBOARD))

//                .setCustomView(
//                    getTabLayoutView(
//                        R.drawable.navigation_icon,
//                        R.string.pdf
//                    )
//                )

        val tabIconColor = ContextCompat.getColor(baseContext, R.color.blue)
        binding.tabLayout.getTabAt(0)?.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                val tabIconColor = ContextCompat.getColor(baseContext, R.color.blue)
                tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                navigateDashboardFragment(tab.tag as Int)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val tabIconColor = ContextCompat.getColor(baseContext, R.color.white)
                tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

//        onTabSelection(tab)
            }
        })
    }

    private fun navigateDashboardFragment(pageId: Int) {
        val bundle = Bundle()
        when (pageId) {
        }

        navigateTo(pageId, bundle, true)
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

    private fun getTabLayoutView(@DrawableRes resId: Int, @StringRes section: Int): View {
        val tv = LayoutInflater.from(this).inflate(R.layout.view_custom_tab, null) as TextView
        tv.setText(section)
        tv.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0)
        return tv
    }

    protected fun hideKeyboard(view: View?) {
        if (view == null) return
        hideKeyBoard(view)
    }
}