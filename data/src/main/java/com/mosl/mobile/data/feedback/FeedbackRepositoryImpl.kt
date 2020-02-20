package com.mosl.mobile.data.feedback

import com.mosl.data.api.AGICApi
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.domain.feedback.FeedbackAnswerRequest
import com.mosl.mobile.domain.feedback.FeedbackRepository
import com.mosl.mobile.domain.feedback.FeedbackRequestInfo
import com.mosl.mobile.domain.feedback.FeedbackResponse
import io.reactivex.Observable
import javax.inject.Inject

class FeedbackRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi,
    val userManager: UserManager
) : FeedbackRepository {

    private val mapper = FeedbackMapper
    override fun getFeedbackData(): Observable<List<FeedbackResponse>> {
        val feedbackRequestInfo = FeedbackRequestInfo(
            ClientID = userManager.user.clientId,
            Role = userManager.user.role
        )
        val requestInfo = mapper.getFeedbackRequestMap(feedbackRequestInfo)
        return agicApi.getFeedback(requestInfo).map {
            mapper.transformFeedback(it)
        }
    }

    override fun submitFeedback(answer: FeedbackAnswerRequest): Observable<String> {
        return Observable.just("Sucess")
    }


}


