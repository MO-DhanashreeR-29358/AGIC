package com.mosl.mobile.data.feedback

import com.mosl.data.api.feedback.*
import com.mosl.mobile.domain.feedback.*

object FeedbackMapper {
    fun transformFeedback(
        responseList: List<FeedbackResponseItem>
    ): List<FeedbackResponse> {
        return responseList.map {
            var optionItem: List<OptionsResponse> = mutableListOf()
            it.options?.apply {
                if (this.size > 0) {
                    optionItem = transformOptions(this)
                }
            }
            FeedbackResponse(
                questionId = it.questionId,
                questionTitle = it.questionTitle,
                questionType = it.questionType,
                options = optionItem,
                defaultValue = it.defaultValue
            )
        }
    }

    fun transformOptions(
        responseList: List<OptionsResponseItem>
    ): List<OptionsResponse> {
        return responseList.map {
            OptionsResponse(
                optionId = it.optionId,
                optionName = it.optionName,
                selected = it.selected
            )
        }
    }

    fun getFeedbackRequestMap(feedbackRequest: FeedbackRequestInfo): FeedbackRequest {
        return FeedbackRequest(
            ClientID = feedbackRequest.ClientID,
            Role = feedbackRequest.Role
        )
    }


    fun transformSubmitRequest(
        answerRequest: FeedbackSubmitModel
    ): SubmitFeedbackRequest {
        return SubmitFeedbackRequest(
            userId = answerRequest.userId,
            role = answerRequest.role,
            answers = transformAnswer(answerRequest.answers)
        )
    }

    fun transformAnswer(
        responseList: List<FeedbackAnswer>
    ): List<FeedbackAnswerRequest> {
        return responseList.map {

            when (it) {
                is CheckBoxSelected -> {
                    FeedbackAnswerRequest(
                        questionId = it.questionId,
                        value = it.value,
                        options = transformAnswerOptions(it.options)
                    )
                }

                is RatingBarItem -> {
                    FeedbackAnswerRequest(
                        questionId = it.questionId,
                        value = it.value,
                        options = mutableListOf()
                    )
                }
                is TextInputItem -> {
                    FeedbackAnswerRequest(
                        questionId = it.questionId,
                        value = it.value,
                        options = mutableListOf()
                    )
                }

            }


        }
    }

    fun transformAnswerOptions(
        optionsList: List<OptionsResponse>
    ): List<OptionsSubmitItem> {
        return optionsList.map {
            OptionsSubmitItem(
                optionId = it.optionId,
                selected = it.selected
            )
        }
    }


}