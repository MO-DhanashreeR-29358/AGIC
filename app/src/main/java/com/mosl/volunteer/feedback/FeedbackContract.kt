package com.mosl.volunteer.feedback

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.volunteer.domain.feedback.FeedbackResponse
import com.mosl.volunteer.domain.feedback.FeedbackSubmitModel
import io.reactivex.Observable


interface FeedbackContract : BaseView {

    fun render(uiModel: UiModel<List<FeedbackResponse>>)
    fun onSubmitButtonClicked(): Observable<Unit>
    fun renderSubmit(uiModel: UiModel<String>)
    fun getSurveyRequest(): FeedbackSubmitModel

}