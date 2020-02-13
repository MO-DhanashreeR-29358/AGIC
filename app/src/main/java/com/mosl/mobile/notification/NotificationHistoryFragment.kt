package com.mosl.mobile.notification

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
import com.mosl.mobile.databinding.FragmentNotificationHistoryBinding
import com.mosl.mobile.domain.notification.NotificationResponse
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

class NotificationHistoryFragment : MOSLCommonFragment(), NotificationContract {

    lateinit var binding: FragmentNotificationHistoryBinding
    lateinit var section: DataManagerSection
    @Inject
    lateinit var presenter: NotificationPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationHistoryBinding.bind(view)
        setupRecyclerView()
        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)
    }

    override fun getLayout(): Int {
        return R.layout.fragment_notification_history
    }


    override fun render(uiModel: UiModel<List<NotificationResponse>>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure { error ->
            section.showError(error)
        }
        uiModel.onSuccess {
            section.clearContent()
            for (model in it) {
                section.add(ItemNotificationChild(model))
            }
        }
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }
        section.setHeader(ItemNotificationHeader())

    }
}