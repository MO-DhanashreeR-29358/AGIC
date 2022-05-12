package com.mosl.mobile.data.feedback

import com.mosl.data.api.AGICApi
import com.mosl.mobile.appuser.login.UserManager
import com.mosl.mobile.appuser.login.user
import com.mosl.mobile.domain.feedback.*
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

    override fun submitFeedback(answer: FeedbackSubmitModel): Observable<String> {
        val requestInfo = mapper.transformSubmitRequest(answer)
        return agicApi.submitFeedback(requestInfo)
    }


}


