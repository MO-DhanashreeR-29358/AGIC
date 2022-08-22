package com.mosl.volunteer.data.feedback

import com.mosl.data.api.AGICApi
import com.mosl.volunteer.appuser.login.UserManager
import com.mosl.volunteer.appuser.login.user
import com.mosl.volunteer.domain.feedback.*
import io.reactivex.Observable
import javax.inject.Inject

class FeedbackRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi,
    val userManager: UserManager
) : FeedbackRepository {

    private val mapper = FeedbackMapper
    override fun getFeedbackData(): Observable<List<FeedbackResponse>> {
        val feedbackRequestInfo = FeedbackRequestInfo(
            ClientID = userManager.user.empCode,
            Role = userManager.user.roomNo
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


