package com.mosl.mobile.overviewpager

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mosl.app.coreui.adapters.ViewPagerAdapter
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonPager
import com.mosl.mobile.databinding.FragmentOverviewBinding
import com.mosl.mobile.panel.PanelFragment
import com.mosl.mobile.panelbase.PanelBaseView
import timber.log.Timber

class OverViewPagerFragment : MOSLCommonPager(), PanelBaseView {


    lateinit var binding: FragmentOverviewBinding
    lateinit var pagerAdapter: ViewPagerAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_overview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOverviewBinding.bind(view)
        setUpAdapter()


    }

    override fun getItem(pos: Int): Fragment? {
        return when (pos) {
            0 -> PanelFragment()
            1 -> PanelFragment()
            2 -> PanelFragment()
            else -> null
        }
    }

    override fun getAdapter(): ViewPagerAdapter {
        return pagerAdapter
    }

    private fun setUpAdapter() {
        pagerAdapter = ViewPagerAdapter(childFragmentManager, requireContext(), this)
        pagerAdapter.setFragmentTitles(R.array.tabTitles)
        mPager.adapter = pagerAdapter

        val screenSize = Resources.getSystem().displayMetrics.widthPixels
        mPager.addOnPageChangeListener(pageChangeListener)
        mIndicator.setupWithViewPager(mPager)
        /* mIndicator.tabMode = if (screenSize < 1100)
             TabLayout.MODE_SCROLLABLE
         else
             TabLayout.MODE_FIXED*/
    }

    override fun onPageSelection(position: Int) {
        Timber.d("Page Selected---> %d", position)
        super.onPageSelection(position)
    }

}
