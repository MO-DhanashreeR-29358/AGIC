package com.mosl.mobile.data.feedback

import com.mosl.data.api.feedback.FeedbackResponseItem
import com.mosl.data.api.feedback.OptionsResponseItem
import com.mosl.mobile.domain.feedback.FeedbackResponse
import com.mosl.mobile.domain.feedback.OptionsResponse

object FeedbackMapper {
    fun transformFeedback(
        responseList: List<FeedbackResponseItem>?
    ): List<FeedbackResponse> {
        return responseList!!.map {
            var optionItem: List<OptionsResponse> = mutableListOf()
            if (it.options.size > 0) {
                optionItem = transformOptions(it.options)
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
        responseList: List<OptionsResponseItem>?
    ): List<OptionsResponse> {
        return responseList!!.map {
            OptionsResponse(
                optionId = it.optionId,
                optionName = it.optionName,
                selected = it.selected
            )
        }
    }


}