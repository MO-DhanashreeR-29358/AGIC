package com.mosl.mobile.domain.feedback

import io.reactivex.Observable

interface FeedbackRepository {
    fun getFeedbackData(): Observable<List<FeedbackResponse>>
}