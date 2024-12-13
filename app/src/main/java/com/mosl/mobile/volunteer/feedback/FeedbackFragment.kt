package com.mosl.mobile.volunteer.feedback

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jakewharton.rxbinding3.view.clicks
import com.mosl.app.core.error.ErrorModel
import com.mosl.app.core.error.ErrorState
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.prefs.StringPreference
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.annotations.FeedbackPendingStatus
import com.mosl.mobile.volunteer.appuser.login.UserManager
import com.mosl.mobile.volunteer.appuser.login.user
import com.mosl.mobile.volunteer.core.MOSLCommonFragment
import com.mosl.mobile.volunteer.databinding.FragmentFeedbackBinding
import com.mosl.mobile.volunteer.domain.feedback.*
import com.mosl.mobile.volunteer.feedback.item.CheckBoxQuestionItem
import com.mosl.mobile.volunteer.feedback.item.RatingBarQuestionItem
import com.mosl.mobile.volunteer.feedback.item.TextQuestionItem
import com.mosl.groupiex.helper.groupie.GroupAdapter
import com.mosl.groupiex.helper.groupie.GroupieViewHolder
import com.mosl.groupiex.helper.groupie.Section
import com.mosl.groupiex.helper.databind.BindableItem
import io.reactivex.Observable
import javax.inject.Inject

class FeedbackFragment : MOSLCommonFragment(), FeedbackContract {

    lateinit var binding: FragmentFeedbackBinding

    lateinit var section: DataManagerSection

    @Inject
    lateinit var presenter: FeedbackPresenter
    private val questionAnswerMap = mutableMapOf<String, FeedbackAnswer>()
    @Inject
    lateinit var userManager: UserManager
    @Inject
    lateinit var gson: Gson

    @Inject
    @field:FeedbackPendingStatus
    lateinit var feedbackPendingStatus: StringPreference

    override fun getLayout(): Int {
        return R.layout.fragment_feedback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeedbackBinding.bind(view)
        setupRecyclerView()
        if (feedbackPendingStatus.get().equals("false",ignoreCase = true)) {
            renderSuccess()
        } else {
            presenter.start()
            dataActionSubject.onNext(DataAction.Fetch)
        }

    }


    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<GroupieViewHolder>()
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
            binding.textView.visibility = View.GONE
            binding.btnSubmit.visibility = View.GONE
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

    override fun onSubmitButtonClicked(): Observable<Unit> {
        return binding.btnSubmit.clicks()
    }

    override fun renderSubmit(uiModel: UiModel<String>) {
        section.toggleProgress(uiModel.inProgress)
        uiModel.onFailure { error ->
            section.showError(error)
        }
        uiModel.onSuccess {
            feedbackPendingStatus.set("false")
            renderSuccess()
        }
    }

    private fun renderSuccess() {

        setFeedbackBadgeVisiblity()
        binding.textView.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE
        section.showEmptyView(
            ErrorModel(
                SUCCESS_MSG,
                ErrorState.NO_DATA_ERROR
            )
        )

    }

    override fun isDataEmpty(): Boolean {
        return section.isDataEmpty
    }

    override fun getSurveyRequest(): FeedbackSubmitModel {
        return FeedbackSubmitModel(
            userId = userManager.user.empCode,
            answers = questionAnswerMap.values.toList(),
            role = ""
        )
    }

    companion object {
        const val SUCCESS_MSG = "Thank you we have received your valuable feedback"
    }
}