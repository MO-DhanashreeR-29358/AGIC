package com.mosl.mobile.corporatepager

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.core.MOSLCommonPager
import com.mosl.mobile.corporateschedule.CorporateScheduleFragment
import com.mosl.mobile.databinding.FragmentCorporateSchedulePagerBinding
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDataInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayAttendanceItemInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo
import com.mosl.mobile.panelpager.PanelFragment
import io.reactivex.subjects.PublishSubject
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CorporatePagerFragment : MOSLCommonPager(), CorporateSchedulePagerContract {

    @Inject
    lateinit var userManager: UserManager

    var overViewDayWiseListData = mutableListOf<List<CorporateScheduleResponseDayItemInfo>>()

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

        binding.apply {
            editSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

                    if (p0.length > 0) {
                        recSearch.visibility = View.VISIBLE
                        tabLayout.visibility = View.GONE
                        pager.visibility = View.GONE
                    }
                    else{
                        recSearch.visibility = View.GONE
                        tabLayout.visibility = View.VISIBLE
                        pager.visibility = View.VISIBLE
                    }
                }
            })
        }



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

    override fun onPageSelection(position: Int) {

        if (overViewDayWiseListData.size > position) {
            if (overViewDayWiseListData.get(position).isNullOrEmpty()) {
                tabPublishSubject.onNext(UiModel.error(ServiceFailureException(PanelFragment.NO_DATA_AVAILABLE_FOR_THE_DAY)))
            } else {
                tabPublishSubject.onNext(UiModel.success(overViewDayWiseListData.get(position)))
            }
        } else
            tabPublishSubject.onNext(UiModel.error(ServiceFailureException(PanelFragment.NO_DATA_AVAILABLE_FOR_THE_DAY)))

        super.onPageSelection(position)
    }


    override fun renderPage(uiModel: UiModel<CorporateScheduleResponseDataInfo>) {

        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure {
            if (corporateScheduleData.isNotSet) {
                sampleData()
                val overViewResponse: CorporateScheduleResponseDataInfo =
                    gson.fromJson(
                        corporateScheduleData.get(),
                        CorporateScheduleResponseDataInfo::class.java
                    )
                overViewResponse.responseData()
//                tabPublishSubject.onNext(UiModel.error(it))
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

        pagerSelection()
    }

    fun pagerSelection() {
        val sdf = SimpleDateFormat("YYYY-MM-dd")
        val currentDate = sdf.format(Date())

        val dayArr = userManager.user.day

        val separate1 = dayArr.split(",").toTypedArray()
        Log.d("", separate1.toString())

        if (currentDate.equals(separate1[1]))
            binding.pager.currentItem = 1
        else if (currentDate.equals(separate1[2]))
            binding.pager.currentItem = 2
        else
            binding.pager.currentItem = 0
    }

    fun sampleData() {

        val obj0 = CorporateScheduleResponseRepresentativeItemInfo(
            name = "ICL Android",
            designation = "Android Developer"
        )

        val obj1 = CorporateScheduleResponseRepresentativeItemInfo(
            name = "ICL Java",
            designation = "Java Developer"
        )

        val obj2 = CorporateScheduleResponseRepresentativeItemInfo(
            name = "ICL PHP",
            designation = "PHP Developer"
        )

        var sasas: List<CorporateScheduleResponseRepresentativeItemInfo> = emptyList()
        sasas = sasas + obj0
        sasas = sasas + obj1
        sasas += obj2
        sasas += obj0


        val obj10 = CorporateScheduleResponseDayAttendanceItemInfo(
            FundName = "ICL FIND",
            representative = sasas
        )

        var sa2: List<CorporateScheduleResponseDayAttendanceItemInfo> = emptyList()
        sa2 += obj10
        sa2 += obj10
        sa2 += obj10


        val objCorporateScheduleRequestInfo1 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "2019-08-19T09:00:00",
            roomNumber = "122",
            Attendees = sa2,
            natureOfMeeting = "1-1",
            day = "day1"
        )

        val objCorporateScheduleRequestInfo6 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "2019-08-19T19:00:00",
            roomNumber = "122",
            Attendees = sa2,
            natureOfMeeting = "2-1",
            day = "day1"
        )


        val objCorporateScheduleRequestInfo2 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "2019-08-19T10:00:00",
            roomNumber = "533",
            Attendees = sa2,
            natureOfMeeting = "3-1",
            day = "day1"
        )

        val objCorporateScheduleRequestInfo3 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "2019-08-19T12:00:00",
            roomNumber = "120",
            Attendees = sa2,
            natureOfMeeting = "4-1",
            day = "day2"
        )

        val objCorporateScheduleRequestInfo4 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "2019-08-19T12:30:00",
            roomNumber = "543",
            Attendees = sa2,
            natureOfMeeting = "Workshop",
            day = "day3"
        )

        val objCorporateScheduleRequestInfo5 = CorporateScheduleResponseDayItemInfo(
            shiftTiming = "2019-08-19T13:00:00",
            roomNumber = "463",
            Attendees = sa2,
            natureOfMeeting = "Small Group",
            day = "day3"
        )

        var sa13: List<CorporateScheduleResponseDayItemInfo> = emptyList()
        var sa23: List<CorporateScheduleResponseDayItemInfo> = emptyList()
        var sa33: List<CorporateScheduleResponseDayItemInfo> = emptyList()

        sa13 += objCorporateScheduleRequestInfo1
        sa13 += objCorporateScheduleRequestInfo2
        sa13 += objCorporateScheduleRequestInfo3
        sa13 += objCorporateScheduleRequestInfo6

        sa23 += objCorporateScheduleRequestInfo4
        sa23 += objCorporateScheduleRequestInfo5

        sa33 += objCorporateScheduleRequestInfo6

        overViewDayWiseListData.add(0, sa13)
        overViewDayWiseListData.add(1, sa23)
        overViewDayWiseListData.add(2, sa33)
    }

    private fun CorporateScheduleResponseDataInfo.responseData() {
        overViewDayWiseListData =
            CorporateScheduleDayWiseListData() as MutableList<List<CorporateScheduleResponseDayItemInfo>>

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