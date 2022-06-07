package com.mosl.mobile.overview


import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentOverviewChildBinding
import com.mosl.mobile.domain.overviewpager.OverviewResponseRepresntativeInfo
import com.mosl.mobile.domain.overviewpager.RmClientWiseResponseInfo
import com.mosl.mobile.domain.overviewpager.RmOverViewMeetingResponseInfo
import com.mosl.mobile.overviewpager.OverviewFragment
import com.mosl.mobile.rmclientwise.ExpandableClientViewAdapter
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class OverviewChildFragment : MOSLCommonFragment(), OverviewChildContract {

    private val followUpUiEvent = PublishSubject.create<OverviewUiEvent>()


    lateinit var binding: FragmentOverviewChildBinding
    lateinit var expandAdapter: ExpandableOverViewAdapter
    lateinit var expandAdapterClient: ExpandableClientViewAdapter

    @Inject
    lateinit var presenter: OverviewChildPresenter
    lateinit var section: DataManagerSection
    var listData: MutableList<RmOverViewMeetingResponseInfo> = ArrayList()
    var listDataClient: MutableList<RmClientWiseResponseInfo> = ArrayList()

    lateinit var publishSubject: Observable<UiModel<List<RmOverViewMeetingResponseInfo>>>
    lateinit var publishSubjectClient: Observable<UiModel<List<RmClientWiseResponseInfo>>>

    override fun getLayout(): Int {
        return R.layout.fragment_overview_child
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOverviewChildBinding.bind(view)
        setupRecyclerView()
        publishSubject =
            (parentFragment as? OverviewFragment)?.tabPublishSubject ?: Observable.empty()
        publishSubjectClient =
            (parentFragment as? OverviewFragment)?.tabClientViewPublishSubject ?: Observable.empty()
        presenter.start()
    }

    override fun onItemClicked(): Observable<OverviewUiEvent.OnMoreClick> {
        return followUpUiEvent.ofType()
    }

    override fun renderItemData(repList: List<OverviewResponseRepresntativeInfo>) {
        val dialogFragment = OverviewRepDialogFragment()

        dialogFragment.setData(repList);
        dialogFragment.show(childFragmentManager, "dialog")
    }

    private fun setupRecyclerView() {
//        val groupAdapter = GroupAdapter<ViewHolder>()
//        section = DataManagerSection(onRetryClick)
//        groupAdapter.add(section)
//        binding.recyclerView.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            setHasFixedSize(true)
//            adapter = ItemOverviewExpandable(requireContext(),listData)
////            adapter = groupAdapter
//        }



    }


    override fun onTabClicked(): Observable<UiModel<List<RmOverViewMeetingResponseInfo>>> {
        return publishSubject.ofType()
    }

    override fun onTabClickedClientWise(): Observable<UiModel<List<RmClientWiseResponseInfo>>> {
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

    override fun renderPage(uiModel: UiModel<List<RmOverViewMeetingResponseInfo>>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure { error ->
            binding.errorMSG.visibility = View.VISIBLE
        }
        uiModel.onSuccess {
            listDataClient.clear()
            listData.clear()
            listData.addAll(it)
            if (listData.isEmpty()){
                binding.errorMSG.visibility = View.VISIBLE
            }else{
                binding.errorMSG.visibility = View.GONE
            }
            expandAdapter = ExpandableOverViewAdapter(followUpUiEvent,listData)
            binding.expandableList.setAdapter(expandAdapter)
            binding.expandableList.setOnGroupExpandListener(object :
                ExpandableListView.OnGroupExpandListener {
                var previousGroup = -1

                override fun onGroupExpand(groupPosition: Int) {
                    if (groupPosition != previousGroup)
                        binding.expandableList.collapseGroup(previousGroup)
                    previousGroup = groupPosition
                }
            })
        }
    }

    override fun renderPageClientData(uiModel: UiModel<List<RmClientWiseResponseInfo>>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure { error ->
        }
        uiModel.onSuccess {

        }
    }
}
