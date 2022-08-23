package com.mosl.mobile.volunteer.domain.feedback

import io.reactivex.Observable

interface FeedbackRepository {
    fun getFeedbackData(): Observable<List<FeedbackResponse>>
    fun submitFeedback(answer:FeedbackSubmitModel): Observable<String>
}