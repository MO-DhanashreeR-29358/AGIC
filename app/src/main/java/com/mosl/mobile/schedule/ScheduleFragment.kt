package com.mosl.mobile.schedule

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onSuccess
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentScheduleBinding
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo_Rep
import com.mosl.mobile.schedule.ScheduleUiEvent.OnMoreClick
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class ScheduleFragment : MOSLCommonFragment(), ScheduleContract {

    private val followUpUiEvent = PublishSubject.create<ScheduleUiEvent>()

    @Inject
    lateinit var presenter: SchedulePresenter

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var section: Section

    override fun getLayout(): Int {
        return R.layout.fragment_schedule
    }

//    override fun getScheduleRequestInfo(): ScheduleRequestInfo {
//        return ScheduleRequestInfo(
//            ClientID = "4676",
//            schedule = "2019-08-19",
//            timestamp = "New"
//        )
//    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScheduleBinding.bind(view)
        setupRecyclerView()

        presenter.start()
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = Section()
        groupAdapter.add(section)
        binding.rvScheduleList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
    }

    override fun renderScheduleData(uiModel: UiModel<List<ScheduleResponseItemInfo>>) {

        uiModel.onSuccess {
            for (model in it) {
                section.add(ItemSchedule(model, followUpUiEvent, context!!))
            }
        }
    }

    override fun renderItemData(repoList: List<ScheduleResponseItemInfo_Rep>) {

        val dialogFragment = ScheduleRepDialogFragment()

        dialogFragment.setData(repoList);
        dialogFragment.show(childFragmentManager, "dialog")
    }

    override fun onItemClicked(): Observable<OnMoreClick> {
        return followUpUiEvent.ofType()
    }
}
