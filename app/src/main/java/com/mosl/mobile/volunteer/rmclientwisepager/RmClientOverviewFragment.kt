package com.mosl.mobile.volunteer.rmclientwisepager

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mosl.app.core.error.getErrorOnFailure
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.exception.ServiceFailureException
import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.coreui.adapters.ViewPagerAdapter
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.annotations.OverViewData
import com.mosl.mobile.volunteer.core.MOSLCommonPager
import com.mosl.mobile.volunteer.databinding.FragmentRmClientOverviewBinding
import com.mosl.mobile.volunteer.domain.overviewpager.*
import com.mosl.mobile.volunteer.panelpager.PanelFragment
import com.mosl.mobile.volunteer.rmclientwise.RmClientWiseFragment
import io.reactivex.subjects.PublishSubject
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


class RmClientOverviewFragment : MOSLCommonPager(), RmClientOverviewPagerContract {

    val tabClientViewPublishSubject = PublishSubject.create<UiModel<List<RmClientWiseResponseInfo>>>()
    @Inject
    lateinit var presenter: RmClientOverviewPresenter

    lateinit var binding: FragmentRmClientOverviewBinding
    private lateinit var pagerAdapter: ViewPagerAdapter

    @Inject
    @field:OverViewData
    lateinit var overViewData: StringPreference

    @Inject
    lateinit var gson: Gson

    override fun getLayout(): Int {
        return R.layout.fragment_rm_client_overview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRmClientOverviewBinding.bind(view)

        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)

    }

    override fun getItem(pos: Int): Fragment? {
        return when (pos) {
            0 -> RmClientWiseFragment()
            1 -> RmClientWiseFragment()
            2 -> RmClientWiseFragment()
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
        mPager.offscreenPageLimit = 3
        mPager.addOnPageChangeListener(pageChangeListener)
        mIndicator.setupWithViewPager(mPager)
        binding.group.visibility = View.GONE
    }

    var overClientViewListData = mutableListOf<List<RmClientWiseResponseInfo>>()

    override fun onPageSelection(position: Int) {

            if (overClientViewListData.get(position).isNullOrEmpty()) {
                tabClientViewPublishSubject.onNext(UiModel.error(ServiceFailureException(PanelFragment.NO_DATA_AVAILABLE_FOR_THE_DAY)))
            } else {
                tabClientViewPublishSubject.onNext(UiModel.success(overClientViewListData[position]))
            }
        super.onPageSelection(position)
    }


    override fun renderClientWiseData(uiModel: UiModel<RmClientWiseResponseDataInfo>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure {
            if (overViewData.get().isEmpty()) {
                binding.rmClientBtnRetry.setOnClickListener(onRetryClick)
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


    fun showMessage(message: String?, typeOfError: Int) {
        binding.rmClientTxtErrorMessage.text=message
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
            binding.rmClientBtnRetry.visibility = View.GONE
        } else if (typeOfError == NETWORK_FAILURE_ERROR) {
            iconDrawable = R.drawable.ic_network
            binding.rmClientBtnRetry.visibility = View.VISIBLE
        } else {
            iconDrawable = R.drawable.ic_service
            binding.rmClientBtnRetry.visibility = View.VISIBLE
        }
        binding.imgErrorIcon.visibility = View.VISIBLE
        binding.imgErrorIcon.setImageResource(iconDrawable)
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
        return overClientViewListData.isEmpty()
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