package com.mosl.mobile.data.feedback

import com.mosl.data.api.AGICApi
import com.mosl.data.api.feedback.FeedbackResponseItem
import com.mosl.data.api.feedback.OptionsResponseItem
import com.mosl.mobile.domain.feedback.FeedbackRepository
import com.mosl.mobile.domain.feedback.FeedbackResponse
import io.reactivex.Observable
import javax.inject.Inject

class FeedbackRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : FeedbackRepository {

    private val mapper = FeedbackMapper
    override fun getFeedbackData(): Observable<List<FeedbackResponse>> {
        /* return agicApi.getNotification().map {
             mapper.transformNotification(it.data)
         }*/
        return getList().map {
            mapper.transformFeedback(it)
        }
    }

    private fun getList(): Observable<List<FeedbackResponseItem>> {
        return Observable.just(
            listOf(
                FeedbackResponseItem(
                    questionId = "1",
                    questionTitle = "Overall experience at Conference",
                    questionType = "rating",
                    options = mutableListOf(),
                    defaultValue = "3"
                ),
                FeedbackResponseItem(
                    questionId = "2",
                    questionTitle = "Interactions with corporates",
                    questionType = "rating",
                    options = mutableListOf(),
                    defaultValue = "3"
                ),
                FeedbackResponseItem(
                    questionId = "5",
                    questionTitle = "Funds you would like to meet in the future",
                    questionType = "input",
                    options = mutableListOf(),
                    defaultValue = "test"
                ),
                FeedbackResponseItem(
                    questionId = "6",
                    questionTitle = "Would you be interested in the doing Offshore Roadshows with us?",
                    questionType = "options",
                    options = listOf(
                        OptionsResponseItem(
                            optionId = "1",
                            optionName = "UK , Europe",
                            selected = true
                        ),
                        OptionsResponseItem(optionId = "2", optionName = "USA", selected = false),
                        OptionsResponseItem(
                            optionId = "3",
                            optionName = "Hong Kong, Singapore, Taiwan & Korea",
                            selected = false
                        ),
                        OptionsResponseItem(
                            optionId = "4",
                            optionName = "Mumbai & Chennai",
                            selected = false
                        )
                    ),
                    defaultValue = ""
                ),
                FeedbackResponseItem(
                    questionId = "7",
                    questionTitle = "Other Comments : ",
                    questionType = "input",
                    options = mutableListOf(),
                    defaultValue = "testing purpose"
                )

            )
        )
    }
}


