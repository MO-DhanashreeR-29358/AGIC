package com.mosl.mobile.dashboardnew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayout
import com.mosl.app.core.utils.hideKeyBoard
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.dashboard.DashboardPresenter
import com.mosl.mobile.databinding.FragmentDashboardNBinding
import com.mosl.mobile.databinding.FragmentOverviewBinding
import com.mosl.mobile.nav.DebugConstants
import com.xwray.groupie.Section
import javax.inject.Inject

class OverViewFragment : MOSLCommonFragment() {

    lateinit var binding: FragmentOverviewBinding


    override fun getLayout(): Int {
        return R.layout.fragment_overview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOverviewBinding.bind(view)

        setupTabs()
    }

    private fun setupTabs() {

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.day1).setTag(DebugConstants.NAVIGATE_TO_DASHBOARD))

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.day2).setTag(DebugConstants.NAVIGATE_TO_OVERVIEW))

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.day3).setTag(DebugConstants.NAVIGATE_TO_OVERVIEW))




        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

//    private fun getTabLayoutView(@StringRes section: Int): View {
//        val tv = LayoutInflater.from(context).inflate(R.layout.view_custom_tab, null) as TextView
//        tv.setText(section)
//        tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
//        return tv
//    }

    private fun navigateDashboardFragment(pageId: Int) {
        val bundle = Bundle()
        when (pageId) {
        }

        navigateTo(pageId, bundle, true)
    }

}
