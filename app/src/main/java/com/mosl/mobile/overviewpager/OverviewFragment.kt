package com.mosl.mobile.overviewpager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.jakewharton.rxbinding3.view.clicks
import com.mosl.app.core.error.getErrorOnFailure
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.exception.ServiceFailureException
import com.mosl.app.core.prefs.BooleanPreference
import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.coreui.adapters.ViewPagerAdapter
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.mobile.R
import com.mosl.mobile.annotations.ClientViewType
import com.mosl.mobile.annotations.OverViewData
import com.mosl.mobile.core.MOSLCommonPager
import com.mosl.mobile.databinding.FragmentOverviewBinding
import com.mosl.mobile.domain.overviewpager.*
import com.mosl.mobile.overview.OverviewChildFragment
import com.mosl.mobile.panelpager.PanelFragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


class OverviewFragment : MOSLCommonPager(), OverviewPagerContract {

    val tabPublishSubject = PublishSubject.create<UiModel<List<RmOverViewMeetingResponseInfo>>>()
    val tabClientViewPublishSubject = PublishSubject.create<UiModel<List<RmClientWiseResponseInfo>>>()
    @Inject
    lateinit var presenter: OverviewPresenter

    lateinit var binding: FragmentOverviewBinding
    private lateinit var pagerAdapter: ViewPagerAdapter
    private var isDayWise = true

    @Inject
    @field:OverViewData
    lateinit var overViewData: StringPreference

    @Inject
    @field:ClientViewType
    lateinit var clientViewType: BooleanPreference

    @Inject
    lateinit var gson: Gson

    override fun getLayout(): Int {
        return R.layout.fragment_overview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOverviewBinding.bind(view)

        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)

        isDayWise = clientViewType.get()
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
        binding.tabLayout.visibility = View.VISIBLE
        pagerAdapter = ViewPagerAdapter(childFragmentManager, requireContext(), this)
        pagerAdapter.setFragmentTitles(R.array.tabTitles)
        mPager.adapter = pagerAdapter
        mPager.addOnPageChangeListener(pageChangeListener)
        mIndicator.setupWithViewPager(mPager)
        binding.group.visibility = View.GONE
    }

    var overViewDayWiseListData = mutableListOf<List<RmOverViewMeetingResponseInfo>>()
    var overClientViewListData = mutableListOf<List<RmClientWiseResponseInfo>>()

    override fun onPageSelection(position: Int) {

        if (clientViewType.get()) {
            if (overViewDayWiseListData.get(position).isNullOrEmpty()) {
                tabPublishSubject.onNext(UiModel.error(ServiceFailureException(PanelFragment.NO_DATA_AVAILABLE_FOR_THE_DAY)))
            } else {
                tabPublishSubject.onNext(UiModel.success(overViewDayWiseListData[position]))
            }
        }else{
            if (overClientViewListData.get(position).isNullOrEmpty()) {
                tabClientViewPublishSubject.onNext(UiModel.error(ServiceFailureException(PanelFragment.NO_DATA_AVAILABLE_FOR_THE_DAY)))
            } else {
                tabClientViewPublishSubject.onNext(UiModel.success(overClientViewListData[position]))
            }
        }
        super.onPageSelection(position)
    }


    override fun renderScheduleData(uiModel: UiModel<RmOverviewResponseDataInfo>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure {
            if (overViewData.get().isEmpty()) {
                binding.btnRetry.setOnClickListener(onRetryClick)
                showMessage( it.getErrorOnFailure().getResolvedMessage(requireContext()),checkException(it))
            } else {
                setUpAdapter()
                val overViewResponse: RmOverviewResponseDataInfo =
                    gson.fromJson(overViewData.get(), RmOverviewResponseDataInfo::class.java)
                overViewResponse.responseData()
            }

        }

        uiModel.onSuccess {
            setUpAdapter()
            val json = gson.toJson(it)
            overViewData.set(json)
            it.responseData()
        }
    }

    override fun renderClientWiseData(uiModel: UiModel<RmClientWiseResponseDataInfo>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure {
            if (overViewData.get().isEmpty()) {
                binding.btnRetry.setOnClickListener(onRetryClick)
                showMessage( it.getErrorOnFailure().getResolvedMessage(requireContext()),checkException(it))
            } else {
                setUpAdapter()
                val overViewResponse: RmClientWiseResponseDataInfo =
                    gson.fromJson(overViewData.get(), RmClientWiseResponseDataInfo::class.java)
                overViewResponse.clientResponseData()
            }

        }

        uiModel.onSuccess {
            setUpAdapter()
            val json = gson.toJson(it)
            overViewData.set(json)
            it.clientResponseData()
        }
    }

    override fun onClientWiseClicked(): Observable<Unit> {
        clientViewType.set(false)
        return binding.btnClientWise.clicks()
    }

    override fun onDayWiseClicked(): Observable<Unit> {
        clientViewType.set(true)
        return binding.btnDayWise.clicks()
    }

    fun showMessage(message: String?, typeOfError: Int) {
        binding.txtErrorMessage.text=message
        showIcon(typeOfError)
        binding.group.visibility = View.VISIBLE
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


    private fun RmOverviewResponseDataInfo.responseData() {
        overViewDayWiseListData =
            RmOverViewDayWiseListData() as MutableList<List<RmOverViewMeetingResponseInfo>>
        val dayOverView = RmOverViewDayWiseListData().get(0)
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

    private fun RmClientWiseResponseDataInfo.clientResponseData() {
        overClientViewListData =
            rmOverViewClientWiseListData() as MutableList<List<RmClientWiseResponseInfo>>
        val dayOverView = rmOverViewClientWiseListData().get(0)
        if (dayOverView.isNotEmpty()) {
            tabClientViewPublishSubject.onNext(UiModel.success(dayOverView))
        } else {
            tabClientViewPublishSubject.onNext(
                UiModel.error(
                    ServiceFailureException(
                        NO_DATA_AVAILABLE_FOR_THE_DAY
                    )
                )
            )
        }
    }

    override fun isDataEmpty(): Boolean {
        return overViewDayWiseListData.isEmpty()
    }

    companion object {
        const val NO_DATA_AVAILABLE_FOR_THE_DAY = "No data available for the day"
        const val NETWORK_FAILURE_ERROR = 0
        const val SERVICE_FAILURE_ERROR:Int = 1
        const val DATA_NOT_PRESENT = 2
        const val UNKNOWN_FAILURE:Int = 3
        const val  VALIDATION_ERROR = 4
    }
}