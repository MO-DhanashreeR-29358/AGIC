package com.mosl.mobile.overviewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.exception.ServiceFailureException
import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.coreui.adapters.ViewPagerAdapter
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.annotations.OverViewData
import com.mosl.mobile.core.MOSLCommonPager
import com.mosl.mobile.databinding.FragmentOverviewBinding
import com.mosl.mobile.domain.overviewpager.OverviewResponseDataInfo
import com.mosl.mobile.domain.overviewpager.OverviewResponseDayItemInfo
import com.mosl.mobile.overview.OverviewChildFragment
import com.mosl.mobile.panelpager.PanelFragment
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class OverviewFragment : MOSLCommonPager(), OverviewPagerContract {

    val tabPublishSubject = PublishSubject.create<UiModel<List<OverviewResponseDayItemInfo>>>()
    private lateinit var section: DataManagerSection

    @Inject
    lateinit var presenter: OverviewPresenter

    lateinit var binding: FragmentOverviewBinding
    private lateinit var pagerAdapter: ViewPagerAdapter

    @Inject
    @field:OverViewData
    lateinit var overViewData: StringPreference

    @Inject
    lateinit var gson: Gson

    override fun getLayout(): Int {
        return R.layout.fragment_overview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOverviewBinding.bind(view)
        setUpAdapter()

        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)

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
        mPager.addOnPageChangeListener(pageChangeListener)
        mIndicator.setupWithViewPager(mPager)

    }

    var overViewDayWiseListData = mutableListOf<List<OverviewResponseDayItemInfo>>()

    override fun onPageSelection(position: Int) {

        if (overViewDayWiseListData.get(position).isNullOrEmpty()) {
            tabPublishSubject.onNext(UiModel.error(ServiceFailureException(PanelFragment.NO_DATA_AVAILABLE_FOR_THE_DAY)))
        } else {
            tabPublishSubject.onNext(UiModel.success(overViewDayWiseListData.get(position)))
        }
        super.onPageSelection(position)
    }


    override fun renderScheduleData(uiModel: UiModel<OverviewResponseDataInfo>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure {
            if (overViewData.get().isEmpty()) {

                tabPublishSubject.onNext(UiModel.error(it))
            } else {
                val overViewResponse: OverviewResponseDataInfo =
                    gson.fromJson(overViewData.get(), OverviewResponseDataInfo::class.java)
                overViewResponse.responseData()
            }

        }

        uiModel.onSuccess {
            val json = gson.toJson(it)
            overViewData.set(json)
            it.responseData()

        }
    }

    private fun OverviewResponseDataInfo.responseData() {
        overViewDayWiseListData =
            OverViewDayWiseListData() as MutableList<List<OverviewResponseDayItemInfo>>
        val dayOverView = OverViewDayWiseListData().get(0)
        if (dayOverView.isNotEmpty()) {
            tabPublishSubject.onNext(UiModel.success(dayOverView))
        } else {
            tabPublishSubject.onNext(
                UiModel.error(
                    ServiceFailureException(
                        NO_DATA_AVAILABLE_FOR_THE_DAY
                    )
                )
            )
        }
    }

    companion object {
        const val NO_DATA_AVAILABLE_FOR_THE_DAY = "No data available for the day"
    }
}