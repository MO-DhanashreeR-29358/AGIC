package com.mosl.mobile.feedback

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
import com.mosl.mobile.databinding.FragmentFeedbackBinding
import com.mosl.mobile.domain.feedback.*
import com.mosl.mobile.feedback.item.CheckBoxQuestionItem
import com.mosl.mobile.feedback.item.RatingBarQuestionItem
import com.mosl.mobile.feedback.item.TextQuestionItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

class FeedbackFragment : MOSLCommonFragment(), FeedbackContract {

    lateinit var binding: FragmentFeedbackBinding

    lateinit var section: DataManagerSection

    @Inject
    lateinit var presenter: FeedbackPresenter
    private val questionAnswerMap = mutableMapOf<String, FeedbackAnswer>()

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

    private val onCheckBoxSelectedListener = object :
        CheckBoxQuestionItem.OnCheckBoxSelectedListener {
        override fun onCheckBoxSelected(answer: FeedbackAnswer) {
            questionAnswerMap[answer.questionId] = answer
        }
    }

    private val onTextEnteredListener = object : TextQuestionItem.OnTextEnteredListener {
        override fun onTextEntered(answer: FeedbackAnswer) {
            questionAnswerMap[answer.questionId] = answer
        }
    }

    private val OnRatingBarSelectedListener =
        object : RatingBarQuestionItem.OnRatingBarSelectedListener {
            override fun onRatingBarSelected(answer: FeedbackAnswer) {
                questionAnswerMap[answer.questionId] = answer
            }
        }

    override fun render(uiModel: UiModel<List<FeedbackResponse>>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure { error ->
            section.showError(error)
        }
        uiModel.onSuccess {
            section.clearContent()
            section.update(it.map { question ->
                when (question.questionType) {
                    "rating" -> {
                        questionAnswerMap[question.questionId] = RatingBarItem(
                            questionId = question.questionId
                        )
                        RatingBarQuestionItem(question, OnRatingBarSelectedListener)
                    }
                    "options" -> {
                        questionAnswerMap[question.questionId] = CheckBoxSelected(
                            questionId = question.questionId,
                            options = question.options
                        )
                        CheckBoxQuestionItem(question, onCheckBoxSelectedListener)
                    }
                    "input" -> {
                        questionAnswerMap[question.questionId] = TextInputItem(
                            questionId = question.questionId
                        )
                        TextQuestionItem(question, onTextEnteredListener)
                    }

                    else -> {
                        questionAnswerMap[question.questionId] = TextInputItem(
                            questionId = question.questionId
                        )
                        TextQuestionItem(question, onTextEnteredListener)
                    }

                }
            })

        }
    }
}