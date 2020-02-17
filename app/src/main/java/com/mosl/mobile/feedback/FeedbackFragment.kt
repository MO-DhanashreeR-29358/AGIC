package com.mosl.mobile.feedback

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.app.core.events.DataAction
import com.mosl.commons.data.UiModel
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentFeedbackBinding
import com.mosl.mobile.domain.notification.NotificationResponse
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

class FeedbackFragment : MOSLCommonFragment(), FeedbackContract {

    lateinit var binding: FragmentFeedbackBinding

    lateinit var section: DataManagerSection

    @Inject
    lateinit var presenter: FeedbackPresenter

    override fun getLayout(): Int {
        return R.layout.fragment_feedback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeedbackBinding.bind(view)
        setupRecyclerView()
        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)
    }


    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>()
        section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)
        binding.rvQuestions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = groupAdapter
        }

    }

    override fun render(uiModel: UiModel<List<NotificationResponse>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}