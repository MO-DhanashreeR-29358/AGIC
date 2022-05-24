package com.mosl.mobile.rmclientwise

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentRmClientwiseBinding
import com.mosl.mobile.domain.overviewpager.RmClientWiseResponseInfo
import com.mosl.mobile.overview.OverviewChildFragment
import com.mosl.mobile.rmclientwisepager.RmClientOverviewFragment
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import javax.inject.Inject

class RmClientWiseFragment : MOSLCommonFragment(), RmClientWiseContract {


    lateinit var binding: FragmentRmClientwiseBinding
    lateinit var expandAdapterClient: ExpandableClientViewAdapter

    @Inject
    lateinit var presenter: RmClientWisePresenter
    var listDataClient: MutableList<RmClientWiseResponseInfo> = ArrayList()

    lateinit var publishSubjectClient: Observable<UiModel<List<RmClientWiseResponseInfo>>>

    override fun getLayout(): Int {
        return R.layout.fragment_rm_clientwise
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRmClientwiseBinding.bind(view)
        publishSubjectClient =
            (parentFragment as? RmClientOverviewFragment)?.tabClientViewPublishSubject ?: Observable.empty()
        presenter.start()
    }

    override fun onTabClicked(): Observable<UiModel<List<RmClientWiseResponseInfo>>> {
        return publishSubjectClient.ofType()
    }

    companion object {

        @JvmStatic
        fun newInstance(
        ): OverviewChildFragment {
            val fragment = OverviewChildFragment()

            return fragment
        }
    }

    override fun renderPage(uiModel: UiModel<List<RmClientWiseResponseInfo>>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure { error ->
        }
        uiModel.onSuccess {

            listDataClient.clear()
            listDataClient.addAll(it)
            expandAdapterClient = ExpandableClientViewAdapter(requireContext(),listDataClient)
            binding.rmClientWiseExpandableList.setAdapter(expandAdapterClient)
            binding.rmClientWiseExpandableList.setOnGroupExpandListener(object :
                ExpandableListView.OnGroupExpandListener {
                var previousGroup = -1

                override fun onGroupExpand(groupPosition: Int) {
                    if (groupPosition != previousGroup)
                        binding.rmClientWiseExpandableList.collapseGroup(previousGroup)
                    previousGroup = groupPosition
                }
            })
        }
    }

}