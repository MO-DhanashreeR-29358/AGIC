package com.mosl.mobile.feedback

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jakewharton.rxbinding3.view.clicks
import com.mosl.app.core.error.ErrorModel
import com.mosl.app.core.error.ErrorState
import com.mosl.app.core.events.DataAction
import com.mosl.app.core.prefs.BooleanPreference
import com.mosl.commons.data.UiModel
import com.mosl.commons.data.onFailure
import com.mosl.commons.data.onSuccess
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.annotations.FeedbackStatus
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.core.MOSLCommonFragment
import com.mosl.mobile.databinding.FragmentFeedbackBinding
import com.mosl.mobile.domain.feedback.*
import com.mosl.mobile.feedback.item.CheckBoxQuestionItem
import com.mosl.mobile.feedback.item.RatingBarQuestionItem
import com.mosl.mobile.feedback.item.TextQuestionItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
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
    @field:FeedbackStatus
    lateinit var feedbackStatus: BooleanPreference

    override fun getLayout(): Int {
        return R.layout.fragment_feedback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeedbackBinding.bind(view)
        setupRecyclerView()
//        if (feedbackStatus.get()) {
//            renderSuccess()
//        } else {
        presenter.start()
        dataActionSubject.onNext(DataAction.Fetch)
//        }

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

    override fun onSubmitButtonClicked(): Observable<Unit> {
        return binding.btnSubmit.clicks()
    }

    override fun renderSubmit(uiModel: UiModel<String>) {
        toggleProgressBar(uiModel.inProgress)
        uiModel.onFailure { error ->
            section.showError(error)
        }
        uiModel.onSuccess {
            renderSuccess(it)
            feedbackStatus.set(true)
        }
    }

    private fun renderSuccess(message: String) {
        feedbackStatus.set(false)
        setFeedbackBadgeVisiblity()
        binding.textView.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE
        section.showEmptyView(
            ErrorModel(
                message,
                ErrorState.NO_DATA_ERROR
            )
        )

    }

    override fun getSurveyRequest(): FeedbackSubmitModel {
        return FeedbackSubmitModel(
            userId = userManager.user.clientId,
            answers = questionAnswerMap.values.toList(),
            role = userManager.user.role
        )
    }

    companion object {
        const val SUCCESS_MSG = "Thank you we have received your valuable feedback"
    }
}