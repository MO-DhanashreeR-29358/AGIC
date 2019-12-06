package com.mosl.mobile.schedule

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.app.core.events.DataAction
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentScheduleBinding
import com.mosl.mobile.domain.schedule.ScheduleRepresentativeItemData
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.schedule.ScheduleUiEvent.ItemClick
import com.mosl.mobile.schedule.ScheduleUiEvent.OnMoreClick
import com.mosl.shared.util.addToCalendart
import com.mosl.shared.util.checkTimeIsBetween
import com.mosl.shared.util.getTimeAMPM
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.domain.schedule.ScheduleResponse
import com.mosl.mobile.login.UserManagerImpl


class ScheduleFragment : MOSLCommonFragment(), ScheduleContract {

    private val followUpUiEvent = PublishSubject.create<ScheduleUiEvent>()

    @Inject
    lateinit var presenter: SchedulePresenter

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var section: DataManagerSection
    @Inject
    lateinit var userManager: UserManagerImpl
    override fun getLayout(): Int {
        return R.layout.fragment_schedule
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScheduleBinding.bind(view)
        setupRecyclerView()

        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        binding.rvScheduleList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
    }

    override fun renderScheduleData(uiModel: UiModel<ScheduleResponse>) {
        uiModel.onFailure { it ->
            section.showError(it)
        }

        uiModel.onSuccess {

            activity?.let {activity ->
                (activity as MOSLCommonActivity).pdfLink=it.PDFLink!!
            }
            userManager.savePdf(it.PDFLink!!)

            section.clearContent()
            var curentPos = -1;
            var curentPosBool = false;
            val sortedList = it.Data.sortedWith(compareBy({ it.startTime }))

            for ((i, model) in sortedList.withIndex()) {

                val startTime = getTimeAMPM(model.startTime)
                val endTime = getTimeAMPM(model.endTime)


                if (checkTimeIsBetween(startTime, endTime) && !curentPosBool) {
                    curentPos = i
                    curentPosBool = true
                }

                section.add(ItemSchedule(model, followUpUiEvent, requireContext(), curentPos))
            }
        }
    }


    override fun renderItemData(repList: List<ScheduleRepresentativeItemData>) {

        val dialogFragment = ScheduleRepDialogFragment()

        dialogFragment.setData(repList);
        dialogFragment.show(childFragmentManager, "dialog")
    }

    override fun renderItemToCalender(objScheduleResponseItemInfo: ScheduleResponseItemInfo) {

        addToCalendart(
            context!!,
            objScheduleResponseItemInfo.companyName,
            objScheduleResponseItemInfo.natureOfMeeting,
            objScheduleResponseItemInfo.roomNo,
            objScheduleResponseItemInfo.startTime,
            objScheduleResponseItemInfo.endTime
        )
    }

    override fun isDataEmpty(): Boolean {
        return true
    }

    override fun onItemClicked(): Observable<OnMoreClick> {
        return followUpUiEvent.ofType()
    }

    override fun onItemClickedMain(): Observable<ItemClick> {
        return followUpUiEvent.ofType()
    }
}