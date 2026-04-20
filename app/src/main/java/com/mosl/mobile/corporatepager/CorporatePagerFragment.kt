package com.mosl.mobile.corporatepager

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mosl.app.core.error.getErrorOnFailure
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.exception.ServiceFailureException
import com.mosl.app.core.prefs.IntegerPreference
import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.coreui.adapters.ViewPagerAdapter
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.annotations.CorporateScheduleData
import com.mosl.mobile.annotations.FeedbackPendingStatus
import com.mosl.mobile.annotations.NotificationCount
import com.mosl.mobile.core.MOSLCommonPager
import com.mosl.mobile.corporateschedule.CorporateScheduleFragment
import com.mosl.mobile.databinding.FragmentCorporateSchedulePagerBinding
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDataInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.mosl.mobile.domain.corporateschedulepager.SearchCorporateScheduleItemInfo
import com.mosl.mobile.appuser.login.UserManagerImpl
import com.mosl.mobile.panelpager.PanelFragment
import com.mosl.shared.AppDateFormats.df_Date6
import com.mosl.groupiex.helper.groupie.GroupAdapter
import com.mosl.groupiex.helper.groupie.GroupieViewHolder
//import com.mosl.groupiex.helper.groupie.GroupAdapter
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*
import javax.inject.Inject

class CorporatePagerFragment : MOSLCommonPager(), CorporateSchedulePagerContract {

    @Inject
    lateinit var userManager: UserManagerImpl

    var corporateListData = mutableListOf<List<CorporateScheduleResponseDayItemInfo>>()
    var searchList = mutableListOf<CorporateScheduleResponseDayItemInfo>()

    val tabPublishSubject =
        PublishSubject.create<SchedulePagerAction>()

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

    @Inject
    @field:FeedbackPendingStatus
    lateinit var feedbackPendingStatus: StringPreference

    @Inject
    @field:NotificationCount
    lateinit var notificationCount: IntegerPreference

    private var time: String = ""

    override fun getLayout(): Int {
        return R.layout.fragment_corporate_schedule_pager
    }

    private val followUpUiEventPager = PublishSubject.create<CorporatePagerUiEvent>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCorporateSchedulePagerBinding.bind(view)
        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)
        setUpAdapter()
        binding.apply {
            editSearch.addTextChangedListener(watcher)
        }
    }

    internal val watcher = object : TextWatcher {

        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {

            binding.apply {

                val groupAdapter = GroupAdapter<GroupieViewHolder>()
                searchSection = DataManagerSection(onRetryClick)

                groupAdapter.add(searchSection)
                recSearch.apply {
                    layoutManager =
                        LinearLayoutManager(requireContext())
                    setHasFixedSize(true)
                    adapter = groupAdapter
                }
                recSearch.visibility = View.VISIBLE
                tabLayout.visibility = View.GONE
                pager.visibility = View.GONE
                if (charSequence.length > 0) {
                    val renderSearchList = mutableListOf<SearchCorporateScheduleItemInfo>()
                    searchList.forEach { event ->
                        for (y in 0..((event.Attendees).size - 1)) {
                            if (event.Attendees[y].FundName.toLowerCase(locale = Locale.ENGLISH)
                                    .contains(charSequence.toString().toLowerCase(locale = Locale.ENGLISH))
                            ) {
                                val searchItem: SearchCorporateScheduleItemInfo
                                searchItem = SearchCorporateScheduleItemInfo(
                                    event.Attendees[y].FundName,
                                    event.Attendees[y].FundName,
                                    event.day,
                                    event.shiftTiming
                                )
                                renderSearchList.add(searchItem)

                            }
                            for (z in 0..((event.Attendees[y].representative).size - 1)) {
                                if (event.Attendees[y].representative[z].name.toLowerCase(
                                        locale = Locale.ENGLISH
                                    ).contains(
                                        charSequence.toString().toLowerCase(locale = Locale.ENGLISH)
                                    )
                                ) {
                                    val searchItem: SearchCorporateScheduleItemInfo
                                    searchItem = SearchCorporateScheduleItemInfo(
                                        event.Attendees[y].FundName,
                                        event.Attendees[y].representative[z].name,
                                        event.day,
                                        event.shiftTiming
                                    )
                                    renderSearchList.add(searchItem)
                                }
                            }


                        }

                    }
                    val sortingList = renderSearchList.sortedWith(compareBy({ it.represntiveName }))
                    searchSection.setContent(sortingList.map {
                        ItemSearchCorporateSchedule(
                            it,
                            followUpUiEventPager
                        )
                    })
                    groupAdapter.notifyDataSetChanged()
                } else {
                    recSearch.visibility = View.GONE
                    tabLayout.visibility = View.VISIBLE
                    pager.visibility = View.VISIBLE
                }
            }
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
        pagerAdapter = ViewPagerAdapter(childFragmentManager, requireContext(), this)
        pagerAdapter.setFragmentTitles(com.mosl.mobile.coreui.R.array.tabTitles)
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
        if (searchCorporateScheduleItemInfo.day.equals("day1", ignoreCase = true))
            pagerSelection(0)
        else if (searchCorporateScheduleItemInfo.day.equals("day2", ignoreCase = true))
            pagerSelection(1)
        else if (searchCorporateScheduleItemInfo.day.equals("day3", ignoreCase = true))
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

        if (corporateListData.size > position) {
            if (corporateListData.get(position).isNullOrEmpty()) {
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
                            corporateListData.get(position)
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
            if (corporateScheduleData.get().isEmpty()) {
                feedbackPendingStatus.set("")
                binding.recSearch.visibility = View.GONE
                binding.tabLayout.visibility = View.GONE
                binding.pager.visibility = View.GONE
                binding.editSearch.visibility = View.GONE
                binding.btnRetry.setOnClickListener(onRetryClick)
                showMessage(
                    it.getErrorOnFailure().getResolvedMessage(requireContext()),
                    checkException(it)
                )
            } else {
                setUpAdapter()
                val corporateResponse: CorporateScheduleResponseDataInfo =
                    gson.fromJson(
                        corporateScheduleData.get(),
                        CorporateScheduleResponseDataInfo::class.java
                    )
                corporateResponse.responseData()

            }
        }

        uiModel.onSuccess {
            val json = gson.toJson(it)
            corporateScheduleData.set(json)
            feedbackPendingStatus.set(it.feedbackPending.toString())
            notificationCount.set(it.notificationPending)
            it.responseData()

        }

        pagerSelection(-1)
    }

    fun pagerSelection(pos: Int) {

        binding.pager.currentItem = 0

        if (pos == -1) {
            val currentDate = df_Date6.format(Date())

            val dayArr = ""

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
        binding.groupError.visibility = View.GONE
        binding.recSearch.visibility = View.VISIBLE
        binding.tabLayout.visibility = View.VISIBLE
        binding.pager.visibility = View.VISIBLE
        binding.editSearch.visibility = View.VISIBLE
        setNotificationUpdate()
        setFeedbackBadgeVisiblity()
        pdfLink?.apply {
            userManager.savePdf(this)
        }
        corporateListData =
            CorporateScheduleDayWiseListData() as MutableList<List<CorporateScheduleResponseDayItemInfo>>
        if(data.size>0){
            searchList = data as MutableList<CorporateScheduleResponseDayItemInfo>

        }
        val dayOverView = CorporateScheduleDayWiseListData().get(0)

        if (dayOverView.isNotEmpty()) {
            tabPublishSubject.onNext(
                SchedulePagerAction.PagerClick(
                    UiModel.success(dayOverView),
                    time
                )
            )

        } else {
            tabPublishSubject.onNext(
                SchedulePagerAction.PagerClick(
                    UiModel.error(
                        ServiceFailureException(
                            NO_DATA_AVAILABLE_FOR_THE_DAY
                        ) as Throwable
                    )
                )
            )
        }
    }

    override fun isDataEmpty(): Boolean {
        return corporateListData.isEmpty()
    }

    fun showMessage(message: String?, typeOfError: Int) {
        binding.txtErrorMessage.text = message
        showIcon(typeOfError)
        binding.groupError.visibility = View.VISIBLE
    }

    fun checkException(t: Throwable?): Int {
        return if (t is UnknownHostException || t is ConnectException) {
            NETWORK_FAILURE_ERROR
        } else if (t is SocketTimeoutException) {
            SERVICE_FAILURE_ERROR
        } else if (t is ServiceFailureException) {
            DATA_NOT_PRESENT
        } else {
            UNKNOWN_FAILURE
        }
    }

    private fun showIcon(typeOfError: Int) {
        val iconDrawable: Int
        if (typeOfError == DATA_NOT_PRESENT) {
            iconDrawable = R.drawable.ic_description
            binding.btnRetry.setVisibility(View.GONE)
        } else if (typeOfError == NETWORK_FAILURE_ERROR) {
            iconDrawable = R.drawable.ic_network
            binding.btnRetry.setVisibility(View.VISIBLE)
        } else {
            iconDrawable = R.drawable.ic_service
            binding.btnRetry.setVisibility(View.VISIBLE)
        }
        binding.imgErrorIcon.setVisibility(View.VISIBLE)
        binding.imgErrorIcon.setImageResource(iconDrawable)
    }

    companion object {
        const val NO_DATA_AVAILABLE_FOR_THE_DAY = "No data available for the day"
        const val NETWORK_FAILURE_ERROR = 0
        const val SERVICE_FAILURE_ERROR: Int = 1
        const val DATA_NOT_PRESENT = 2
        const val UNKNOWN_FAILURE: Int = 3
    }
}