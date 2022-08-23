package com.mosl.mobile.volunteer.notification

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
import com.mosl.mobile.volunteer.annotations.NotificationCount
import com.mosl.mobile.volunteer.annotations.NotificationData
import com.mosl.mobile.volunteer.appuser.login.UserManager
import com.mosl.mobile.volunteer.appuser.login.user
import com.mosl.mobile.volunteer.core.MOSLCommonActivity
import com.mosl.mobile.volunteer.core.MOSLCommonFragment
import com.mosl.mobile.volunteer.databinding.FragmentNotificationHistoryBinding
import com.mosl.mobile.volunteer.domain.notification.NotificationResponse
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class NotificationHistoryFragment : MOSLCommonFragment(), NotificationContract {

    lateinit var binding: FragmentNotificationHistoryBinding
    lateinit var section: DataManagerSection
    @Inject
    lateinit var presenter: NotificationPresenter

    @Inject
    @field:NotificationData
    lateinit var notificationData: StringPreference

    @Inject
    @field:NotificationCount
    lateinit var notificationCount: IntegerPreference

    @Inject
    lateinit var gson: Gson
    @Inject
    lateinit var userManager: UserManager

    private val repsUiEvent = PublishSubject.create<NotificationUiEvent>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationHistoryBinding.bind(view)
        setupRecyclerView()
        setUpView()
        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = DataManagerSection(onRetryClick)
        section.retrySubject = dataActionSubject
        groupAdapter.add(section)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
    }

    private fun setUpView() {
        if (userManager.user.roomNo.equals(MOSLCommonActivity.ROLE_CLIENT, ignoreCase = true)) {
            binding.header.tvCorporate.setText(CORPORATE)

        } else {
            binding.header.tvCorporate.setText(FUND)
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_notification_history
    }


    override fun renderData(uiModel: UiModel<List<NotificationResponse>>) {
        binding.swipeRefresh.toggleProgressBar(section, uiModel.inProgress)
        uiModel.onFailure { error ->
            if (notificationData.get().isEmpty()) {
                section.showError(error)
                section.removeHeader()
                binding.textView.visibility = View.GONE
                binding.header.root.visibility = View.GONE
            } else {
                val type = object : TypeToken<List<NotificationResponse>>() {}.type
                val notificationResponse: List<NotificationResponse> =
                    gson.fromJson(notificationData.get(), type)
                notificationResponse.responseData()
            }
        }

        uiModel.onSuccess {
            val json = gson.toJson(it)
            notificationData.set(json)
            notificationCount.set(0)
            it.responseData()
        }
    }


    override fun swipeRefresh(): Observable<Unit> {
        return binding.swipeRefresh.refreshes()
    }

    private fun List<NotificationResponse>.responseData() {
        if (this.isNotEmpty()) {
            setNotificationUpdate()
            section.clearContent()
            binding.textView.visibility = View.VISIBLE
            section.setContent(this.map { ItemNotificationChild(it, repsUiEvent) })
//            getNotificationData(this).renderUi()
        } else {
            binding.textView.visibility = View.GONE
            section.removeHeader()
            section.showEmptyView(
                ErrorModel(
                    NO_DATA_AVAILABLE_FOR_THE_DAY,
                    ErrorState.NO_DATA_ERROR
                )
            )
        }
    }

    override fun onItemClicked(): Observable<NotificationUiEvent.OnMoreClick> {
        return repsUiEvent.ofType()
    }

    override fun renderItemData(repList: List<String>) {

        val dialogFragment = NotificationRepsDialogFragment()

        dialogFragment.setData(repList);
        dialogFragment.show(childFragmentManager, "dialog")
    }

//    fun List<NotificationModel>.renderUi() {
//        val items = mutableListOf<BindableItem<*>>()
//        map {
//            items.add(ItemNotificationHeaderDate(it.headerTime))
//            it.itemList.map { model ->
//                items.add(ItemNotificationChild(model))
//            }
//        }
//
//        section.update(items)
//    }
//
//    fun getNotificationData(dataList: List<NotificationResponse>): List<NotificationModel> {
//        val notificationListData = mutableListOf<NotificationModel>()
//
//
//        val distinctDatesList = dataList
//            .map {
//                getFormatedDate(
//                    it.meetingDateTime,
//                    AppDateFormats.DF_JSON_DATE_4,
//                    AppDateFormats.DF_JSON_DATE6
//                )
//            }
//            .distinctBy { it }.sortedWith(compareByDescending({ it }))
//
//
//        distinctDatesList.forEachIndexed { indexDate, filteredDates ->
//            val childItemList = mutableListOf<NotificationItemModel>()
//            dataList.forEachIndexed { _, notificationResponse ->
//                if (getFormatedDate(
//                        notificationResponse.meetingDateTime,
//                        AppDateFormats.DF_JSON_DATE_4,
//                        AppDateFormats.DF_JSON_DATE6
//                    ).contains(filteredDates)
//                ) {
//                    childItemList.add(
//                        NotificationItemModel(
//                            notificationResponse.corporateName,
//                            notificationResponse.meetingDateTime,
//                            notificationResponse.previousDateTime,
//                            notificationResponse.type,
//                            notificationResponse.message
//                        )
//                    )
//                }
//            }
//            val sortedList = childItemList.sortedWith(compareByDescending({ it.meetingDateTime }))
//            notificationListData.add(indexDate, NotificationModel(filteredDates, sortedList))
//        }
//
//        return notificationListData
//    }


    override fun isDataEmpty(): Boolean {
        return section.isDataEmpty
    }

    companion object {
        const val NO_DATA_AVAILABLE_FOR_THE_DAY = "No data available for the day"
        const val CORPORATE = "Corporate"
        const val FUND = "Fund"

    }
}