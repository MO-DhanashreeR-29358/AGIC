package com.mosl.mobile.overviewpager

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.mosl.app.coreui.adapters.ViewPagerAdapter
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonPager
import com.mosl.mobile.databinding.FragmentOverviewBinding
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseDataInfo
import com.mosl.mobile.overview.OverviewChildFragment
import timber.log.Timber
import javax.inject.Inject

class OverviewFragment : MOSLCommonPager(), OverviewPagerContract {

    private lateinit var section: DataManagerSection

    @Inject
    lateinit var presenter: OverviewPresenter

    lateinit var binding: FragmentOverviewBinding
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_overview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOverviewBinding.bind(view)
        setUpAdapter()

        presenter.start()

    }

    override fun getItem(pos: Int): Fragment? {
        return when (pos) {
            0 -> OverviewChildFragment()
            1 -> OverviewChildFragment()
            2 -> OverviewChildFragment()
            else -> null
        }
    }

    override fun getAdapter(): ViewPagerAdapter {
        return pagerAdapter
    }

    private fun setUpAdapter() {
        section = DataManagerSection(onRetryClick)

        pagerAdapter = ViewPagerAdapter(childFragmentManager, requireContext(), this)
        pagerAdapter.setFragmentTitles(R.array.tabTitles)
        mPager.adapter = pagerAdapter

        //val screenSize = Resources.getSystem().displayMetrics.widthPixels
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

    override fun renderScheduleData(uiModel: UiModel<OverviewPagerResponseDataInfo>) {
        uiModel.onFailure {
            section.showError(it)
            showOnFailurePopup(it)
        }

        uiModel.onSuccess {

            Log.d("DATA", it.toString())

        }
    }
}
