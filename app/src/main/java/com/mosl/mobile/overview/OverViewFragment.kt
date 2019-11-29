package com.mosl.mobile.overview

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentOverviewBinding

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
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.text_label_day1))

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.text_label_day2))

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.text_label_day3))

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}