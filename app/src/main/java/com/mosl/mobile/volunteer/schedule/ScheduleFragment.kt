package com.mosl.mobile.volunteer.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mosl.app.core.error.ErrorModel
import com.mosl.app.core.error.ErrorState
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.prefs.IntegerPreference
import com.mosl.app.core.prefs.StringPreference
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.annotations.FeedbackPendingStatus
import com.mosl.mobile.volunteer.annotations.NotificationCount
import com.mosl.mobile.volunteer.annotations.ScheduleData
import com.mosl.mobile.volunteer.core.MOSLCommonFragment
import com.mosl.mobile.volunteer.databinding.CalendarAddDialogBinding
import com.mosl.mobile.volunteer.databinding.FragmentScheduleBinding
import com.mosl.mobile.volunteer.domain.schedule.ScheduleRepresentativeItemData
import com.mosl.mobile.volunteer.domain.schedule.ScheduleResponse
import com.mosl.mobile.volunteer.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.volunteer.appuser.login.UserManagerImpl
import com.mosl.mobile.volunteer.schedule.ScheduleUiEvent.ItemClick
import com.mosl.mobile.volunteer.schedule.ScheduleUiEvent.OnMoreClick
import com.mosl.shared.util.addToCalendart
import com.mosl.shared.util.checkTimeIsBetween
import com.mosl.shared.util.getTimeAMPM
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


class ScheduleFragment : MOSLCommonFragment(), ScheduleContract {

    @Inject
    lateinit var presenter: SchedulePresenter

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var section: DataManagerSection
    @Inject
    lateinit var userManager: UserManagerImpl

    @Inject
    @field:ScheduleData
    lateinit var scheduleData: StringPreference

    @Inject
    lateinit var gson: Gson

    @Inject
    @field:FeedbackPendingStatus
    lateinit var feedbackPendingStatus: StringPreference

    @Inject
    @field:NotificationCount
    lateinit var notificationCount: IntegerPreference

    private val followUpUiEvent = PublishSubject.create<ScheduleUiEvent>()

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
        section.toggleProgress(uiModel.inProgress)
        uiModel.onFailure { it ->
            if (scheduleData.get().isEmpty()) {
                section.showError(it)
                feedbackPendingStatus.set("")
            } else {
                val scheduleResponse: ScheduleResponse =
                    gson.fromJson(scheduleData.get(), ScheduleResponse::class.java)
                scheduleResponse.responseData()
            }

        }

        uiModel.onSuccess {
            val json = gson.toJson(it)
            scheduleData.set(json)
            feedbackPendingStatus.set(it.feedbackPending.toString())
            notificationCount.set(it.notificationPending)
            it.responseData()
        }
    }

    private fun ScheduleResponse.responseData() {
        setNotificationUpdate()
        setFeedbackBadgeVisiblity()
        pdfLink?.apply {
            userManager.savePdf(this)
        }
        data?.let {
            if (it.isNotEmpty()) {
                section.clearContent()
                val sortedList = it.sortedWith(compareBy({ it.startTime }))
                var currentTimeBetween = false
                var currentPosition = -1
                for ((i, model) in sortedList.withIndex()) {
                    val startTime = getTimeAMPM(model.startTime)
                    val endTime = getTimeAMPM(model.endTime)

                    if (checkTimeIsBetween(startTime, endTime) && !currentTimeBetween) {
                        currentTimeBetween = true
                        currentPosition = i
                    }

                    section.add(
                        ItemSchedule(
                            model,

                            followUpUiEvent,
                            requireContext(),
                            currentPosition
                        )
                    )
                }
            } else {
                section.showEmptyView(
                    ErrorModel(
                        message,
                        ErrorState.NO_DATA_ERROR
                    )
                )
            }
        }
    }


    override fun renderItemData(repList: List<ScheduleRepresentativeItemData>) {

        val dialogFragment = ScheduleRepDialogFragment()

        dialogFragment.setData(repList);
        dialogFragment.show(childFragmentManager, "dialog")
    }


    lateinit var bindingDialog: CalendarAddDialogBinding

    internal fun calendarDialog(scheduleResponseItemInfo: ScheduleResponseItemInfo) {

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.calendar_add_dialog, null)

        bindingDialog = CalendarAddDialogBinding.bind(mDialogView)

        bindingDialog.apply {

            val mBuilder = AlertDialog.Builder(requireContext()).setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            txtEvent.text = String.format(
                "%s" + "\nDate: " + "%s" +
                        "\nStart Time: " + "%s" +
                        "\nEnd Time: " + "%s",
                scheduleResponseItemInfo.companyName,
                scheduleResponseItemInfo.scheduleDate,
                getTimeAMPM(scheduleResponseItemInfo.startTime),
                getTimeAMPM(scheduleResponseItemInfo.endTime)
            )

            btnYes.setOnClickListener {
                addToCalendart(
                    requireContext(),
                    scheduleResponseItemInfo.companyName,
                    scheduleResponseItemInfo.natureOfMeeting,
                    scheduleResponseItemInfo.roomNo,
                    scheduleResponseItemInfo.startTime,
                    scheduleResponseItemInfo.endTime
                )

                mAlertDialog.dismiss()
            }
            //cancel button click of custom layout
            btnNo.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
            }
        }
    }

    override fun renderItemToCalender(scheduleResponseItemInfo: ScheduleResponseItemInfo) {

        calendarDialog(scheduleResponseItemInfo)
    }

    override fun isDataEmpty(): Boolean {
        return section.isDataEmpty
    }

    override fun onItemClicked(): Observable<OnMoreClick> {
        return followUpUiEvent.ofType()
    }

    override fun onItemClickedMain(): Observable<ItemClick> {
        return followUpUiEvent.ofType()
    }
}