package com.mosl.mobile.feedback

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.feedback.FeedbackAnswerRequest
import com.mosl.mobile.domain.feedback.FeedbackResponse
import io.reactivex.Observable


interface FeedbackContract : BaseView {

    fun render(uiModel: UiModel<List<FeedbackResponse>>)
    fun onSubmitButtonClicked(): Observable<Unit>
    fun renderSubmit(uiModel: UiModel<String>)
    fun getSurveyRequest(): FeedbackAnswerRequest

}