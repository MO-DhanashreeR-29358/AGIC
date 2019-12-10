package com.mosl.mobile.overviewpager

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.mosl.app.core.exception.ServiceFailureException
import com.mosl.app.coreui.adapters.ViewPagerAdapter
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonPager
import com.mosl.mobile.databinding.FragmentOverviewBinding
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseDataInfo
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseDayItemInfo
import com.mosl.mobile.overview.OverviewChildFragment
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class OverviewFragment : MOSLCommonPager(), OverviewPagerContract {

    val tabPublishSubject = PublishSubject.create<UiModel<List<OverviewPagerResponseDayItemInfo>>>()
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

    lateinit var panelDayWiseListData: OverviewPagerResponseDataInfo

    override fun onPageSelection(position: Int) {

        return when (position) {
            0 -> if (panelDayWiseListData.Day1.isNullOrEmpty()) {
                tabPublishSubject.onNext(UiModel.error(ServiceFailureException("No data available for the day")))
            } else {
                tabPublishSubject.onNext(UiModel.success(panelDayWiseListData.Day1))
            }
            1 ->
                if (panelDayWiseListData.Day2.isNullOrEmpty()) {
                    tabPublishSubject.onNext(UiModel.error(ServiceFailureException("No data available for the day")))
                } else {
                    tabPublishSubject.onNext(UiModel.success(panelDayWiseListData.Day2))
                }
            2 ->
                if (panelDayWiseListData.Day3.isNullOrEmpty()) {
                    tabPublishSubject.onNext(UiModel.error(ServiceFailureException("No data available for the day")))
                } else {
                    tabPublishSubject.onNext(UiModel.success(panelDayWiseListData.Day3))
                }
            else ->
                if (panelDayWiseListData.Day1.isNullOrEmpty()) {
                    tabPublishSubject.onNext(UiModel.error(ServiceFailureException("No data available for the day")))
                } else {
                    tabPublishSubject.onNext(UiModel.success(panelDayWiseListData.Day1))
                }
        }

        super.onPageSelection(position)
    }

    override fun renderScheduleData(uiModel: UiModel<OverviewPagerResponseDataInfo>) {

        uiModel.onFailure {
            tabPublishSubject.onNext(UiModel.error(it))
        }

        uiModel.onSuccess {

            Log.d("DATA", it.toString())

            val sortedList1 = it.Day1.sortedWith(compareBy({ it.start_time }))
            val sortedList2 = it.Day2.sortedWith(compareBy({ it.start_time }))
            val sortedList3 = it.Day3.sortedWith(compareBy({ it.start_time }))

            panelDayWiseListData = OverviewPagerResponseDataInfo(
                sortedList1,
                sortedList2,
                sortedList3
            )

            if (panelDayWiseListData.Day1.isNullOrEmpty()) {
                tabPublishSubject.onNext(UiModel.error(ServiceFailureException("No data available for the day")))
            } else {
                tabPublishSubject.onNext(UiModel.success(panelDayWiseListData.Day1))
            }
        }
    }
}