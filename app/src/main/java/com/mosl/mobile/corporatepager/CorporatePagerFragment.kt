package com.mosl.mobile.corporatepager

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.mosl.mobile.domain.corporateschedulepager.SearchCorporateScheduleItemInfo
import com.mosl.mobile.panelpager.PanelFragment
import com.mosl.shared.AppDateFormats.df_Date6
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import java.util.*
import javax.inject.Inject

class CorporatePagerFragment : MOSLCommonPager(), CorporateSchedulePagerContract {

    @Inject
    lateinit var userManager: UserManager

    var overViewDayWiseListData = mutableListOf<List<CorporateScheduleResponseDayItemInfo>>()

    val tabPublishSubject =
        PublishSubject.create<SchedulePagerAction>()

    private lateinit var section: DataManagerSection
    private lateinit var searchSection: DataManagerSection

    @Inject
    lateinit var presenter: CorporateSchedulePagerPresenter

    lateinit var binding: FragmentCorporateSchedulePagerBinding
    private lateinit var pagerAdapter: ViewPagerAdapter

    @Inject
    @field:CorporateScheduleData
    lateinit var corporateScheduleData: StringPreference

    @Inject
    lateinit var gson: Gson

    private var time: String = ""

    override fun getLayout(): Int {
        return R.layout.fragment_corporate_schedule_pager
    }

    private val followUpUiEventPager = PublishSubject.create<CorporatePagerUiEvent>()

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

                    val groupAdapterS = GroupAdapter<ViewHolder>()
                    searchSection = DataManagerSection(onRetryClick)

                    groupAdapterS.add(searchSection)
                    recSearch.apply {
                        layoutManager =
                            LinearLayoutManager(requireContext()) as RecyclerView.LayoutManager?
                        setHasFixedSize(true)
                        adapter = groupAdapterS
                    }

                    recSearch.visibility = View.VISIBLE
                    tabLayout.visibility = View.GONE
                    pager.visibility = View.GONE


                    if (p0.length > 0) {
                        overViewDayWiseListData!!.forEach { event ->

                            for (x in 0..event.size - 1) {

                                for (y in 0..((event[x].Attendees).size - 1)) {

                                    if (event[x].Attendees[y].FundName.toLowerCase().contains(p0.toString().toLowerCase())) {

                                        val sa: SearchCorporateScheduleItemInfo

                                        sa = SearchCorporateScheduleItemInfo(
                                            event[x].Attendees[y].FundName,
                                            event[x].Attendees[y].FundName,
                                            event[x].day,
                                            event[x].shiftTiming
                                        )

                                        searchSection.add(
                                            ItemSearchCorporateSchedule(
                                                sa,
                                                followUpUiEventPager
                                            )
                                        )

                                    } else {

                                        for (z in 0..((event[x].Attendees[y].representative).size - 1)) {

                                            if (event[x].Attendees[y].representative[z].name.toLowerCase().contains(
                                                    p0.toString().toLowerCase()
                                                )
                                            ) {
                                                val sa: SearchCorporateScheduleItemInfo

                                                sa = SearchCorporateScheduleItemInfo(
                                                    event[x].Attendees[y].FundName,
                                                    event[x].Attendees[y].representative[z].name,
                                                    event[x].day,
                                                    event[x].shiftTiming
                                                )

                                                searchSection.add(
                                                    ItemSearchCorporateSchedule(
                                                        sa,
                                                        followUpUiEventPager
                                                    )
                                                )
                                            }
                                        }
                                    }

                                    groupAdapterS.notifyDataSetChanged()
                                }
                            }

                        }

                    } else {
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

    override fun onSearchItemClicked(): Observable<CorporatePagerUiEvent.OnSearchClick> {
        return followUpUiEventPager.ofType()
    }

    override fun renderSearchItemData(searchCorporateScheduleItemInfo: SearchCorporateScheduleItemInfo) {
        time = searchCorporateScheduleItemInfo.shiftTiming
        binding.editSearch.setText("")
        if (searchCorporateScheduleItemInfo.day.equals("day1"))
            pagerSelection(0)
        else if (searchCorporateScheduleItemInfo.day.equals("day2"))
            pagerSelection(1)
        else if (searchCorporateScheduleItemInfo.day.equals("day3"))
            pagerSelection(2)
        else
            pagerSelection(0)

    }

    override fun onBackPressed(): Boolean {

        if (binding.editSearch.text!!.length > 0) {
            binding.editSearch.setText("")
            return false
        } else
            return super.onBackPressed()
    }


    override fun onPageSelection(position: Int) {

        if (overViewDayWiseListData.size > position) {
            if (overViewDayWiseListData.get(position).isNullOrEmpty()) {
                tabPublishSubject.onNext(
                    SchedulePagerAction.PagerClick(
                        UiModel.error(
                            ServiceFailureException(PanelFragment.NO_DATA_AVAILABLE_FOR_THE_DAY)
                        )
                    )
                )
            } else {
                tabPublishSubject.onNext(
                    SchedulePagerAction.PagerClick(
                        UiModel.success(
                            overViewDayWiseListData.get(position)
                        ), time
                    )
                )
            }
        } else
            tabPublishSubject.onNext(
                SchedulePagerAction.PagerClick(
                    UiModel.error(
                        ServiceFailureException(PanelFragment.NO_DATA_AVAILABLE_FOR_THE_DAY)
                    )
                )
            )

        super.onPageSelection(position)
    }


    override fun renderPage(uiModel: UiModel<CorporateScheduleResponseDataInfo>) {

        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure {
            if (corporateScheduleData.isNotSet) {
                tabPublishSubject.onNext(SchedulePagerAction.PagerClick(UiModel.error(it)))
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
            val json = gson.toJson(it)
            corporateScheduleData.set(json)
            it.responseData()
        }

        pagerSelection(-1)
    }

    fun pagerSelection(pos: Int) {

        binding.pager.currentItem = 0

        if (pos == -1) {
            val currentDate = df_Date6.format(Date())

            val dayArr = userManager.user.day

            val separate1 = dayArr.split(",").toTypedArray()

            if (currentDate.equals(separate1[1]))
                binding.pager.currentItem = 1
            else if (currentDate.equals(separate1[2]))
                binding.pager.currentItem = 2
            else
                binding.pager.currentItem = 0
        } else if (pos == 1 || pos == 2)
            binding.pager.currentItem = pos
        else {
            binding.pager.currentItem = 1
            binding.pager.currentItem = pos
        }
    }

    private fun CorporateScheduleResponseDataInfo.responseData() {
        overViewDayWiseListData =
            CorporateScheduleDayWiseListData() as MutableList<List<CorporateScheduleResponseDayItemInfo>>

        val dayOverView = CorporateScheduleDayWiseListData().get(0)

        if (dayOverView.isNotEmpty()) {
            tabPublishSubject.onNext(
                SchedulePagerAction.PagerClick(
                    UiModel.success(dayOverView),
                    time
                )
            )
            setNotificationUpdate(5)
        } else {
            tabPublishSubject.onNext(
                SchedulePagerAction.PagerClick(
                    UiModel.error(
                        ServiceFailureException(
                            NO_DATA_AVAILABLE_FOR_THE_DAY
                        )
                    )
                )
            )
        }
    }

    companion object {
        const val NO_DATA_AVAILABLE_FOR_THE_DAY = "No data available for the day"
    }
}