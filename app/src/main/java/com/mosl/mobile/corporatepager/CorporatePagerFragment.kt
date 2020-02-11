package com.mosl.mobile.corporatepager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mosl.app.core.exception.ServiceFailureException
import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.coreui.adapters.ViewPagerAdapter
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.annotations.CorporateScheduleData
import com.mosl.mobile.core.MOSLCommonPager
import com.mosl.mobile.corporateschedule.CorporateScheduleFragment
import com.mosl.mobile.databinding.FragmentCorporateSchedulePagerBinding
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDataInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayAttendanceItemInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo
import com.mosl.mobile.panelpager.PanelFragment
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CorporatePagerFragment : MOSLCommonPager(), CorporateSchedulePagerContract {

    val tabPublishSubject =
        PublishSubject.create<UiModel<List<CorporateScheduleResponseDayItemInfo>>>()
    private lateinit var section: DataManagerSection

    @Inject
    lateinit var presenter: CorporateSchedulePagerPresenter

    lateinit var binding: FragmentCorporateSchedulePagerBinding
    private lateinit var pagerAdapter: ViewPagerAdapter

    @Inject
    @field:CorporateScheduleData
    lateinit var corporateScheduleData: StringPreference


    @Inject
    lateinit var gson: Gson

    override fun getLayout(): Int {
        return R.layout.fragment_corporate_schedule_pager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCorporateSchedulePagerBinding.bind(view)
        setUpAdapter()

        presenter.start()

    }

    override fun getItem(pos: Int): Fragment? {
        return when (pos) {
            0 -> CorporateScheduleFragment()
            1 -> CorporateScheduleFragment()
            2 -> CorporateScheduleFragment()
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

    var overViewDayWiseListData = mutableListOf<List<CorporateScheduleResponseDayItemInfo>>()

    override fun onPageSelection(position: Int) {

        if (overViewDayWiseListData.size>position){
            if (overViewDayWiseListData.get(position).isNullOrEmpty()) {
                tabPublishSubject.onNext(UiModel.error(ServiceFailureException(PanelFragment.NO_DATA_AVAILABLE_FOR_THE_DAY)))
            } else {
                tabPublishSubject.onNext(UiModel.success(overViewDayWiseListData.get(position)))
            }
        }
        else
            tabPublishSubject.onNext(UiModel.error(ServiceFailureException(PanelFragment.NO_DATA_AVAILABLE_FOR_THE_DAY)))

        super.onPageSelection(position)
    }


    override fun renderPage(uiModel: UiModel<CorporateScheduleResponseDataInfo>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure {

            sampleData()
            if (corporateScheduleData.isNotSet) {

                tabPublishSubject.onNext(UiModel.error(it))
            } else {
                val overViewResponse: CorporateScheduleResponseDataInfo =
                    gson.fromJson(
                        corporateScheduleData.get(),
                        CorporateScheduleResponseDataInfo::class.java
                    )
                overViewResponse.responseData()
            }

        }

        uiModel.onSuccess {
            sampleData()
            val json = gson.toJson(it)
            corporateScheduleData.set(json)
            it.responseData()

        }
    }

    fun sampleData() {

        val obj0 = CorporateScheduleResponseRepresentativeItemInfo(
            name = "ICL FIND",
            designation = "Android Developer"
        )

        var sasas : List<CorporateScheduleResponseRepresentativeItemInfo> = emptyList()
        sasas+=obj0
        sasas+=obj0
        sasas+=obj0



        val obj1 = CorporateScheduleResponseDayAttendanceItemInfo(
            FundName = "ICL FIND",
            representative = sasas
        )

        var sa2 : List<CorporateScheduleResponseDayAttendanceItemInfo> = emptyList()
        sa2+=obj1
        sa2+=obj1
        sa2+=obj1



        val objCorporateScheduleRequestInfo1 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "09.00 AM",
            roomNumber = "122",
            Attendees = sa2,
            natureOfMeeting = "",
            day = "day1"
        )

        val objCorporateScheduleRequestInfo2 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "10.00 AM",
            roomNumber = "533",
            Attendees = sa2,
            natureOfMeeting = "",
            day = "day1"
        )

        val objCorporateScheduleRequestInfo3 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "11.20 AM",
            roomNumber = "120",
            Attendees = sa2,
            natureOfMeeting = "",
            day = "day2"
        )

        val objCorporateScheduleRequestInfo4 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "11.50 AM",
            roomNumber = "543",
            Attendees = sa2,
            natureOfMeeting = "",
            day = "day3"
        )

        val objCorporateScheduleRequestInfo5 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "12.00 PM",
            roomNumber = "463",
            Attendees = sa2,
            natureOfMeeting = "",
            day = "day3"
        )

        var sa13 : List<CorporateScheduleResponseDayItemInfo> = emptyList()
        var sa23 : List<CorporateScheduleResponseDayItemInfo> = emptyList()
        var sa33 : List<CorporateScheduleResponseDayItemInfo> = emptyList()

        sa13+=objCorporateScheduleRequestInfo1
        sa13+=objCorporateScheduleRequestInfo2
        sa13+=objCorporateScheduleRequestInfo3
        sa23+=objCorporateScheduleRequestInfo4
        sa23+=objCorporateScheduleRequestInfo5

        overViewDayWiseListData.add(0, sa13)
        overViewDayWiseListData.add(1, sa23)
        overViewDayWiseListData.add(2, sa33)
    }

    private fun CorporateScheduleResponseDataInfo.responseData() {
        overViewDayWiseListData =
            CorporateScheduleDayWiseListData() as MutableList<List<CorporateScheduleResponseDayItemInfo>>

        sampleData()

        val dayOverView = CorporateScheduleDayWiseListData().get(0)

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