package com.mosl.mobile.volunteer.domain.feedback

data class FeedbackResponse(
    @JvmField val questionId: String,

    @JvmField val questionTitle: String,

    @JvmField val questionType: String,

    @JvmField val options: List<OptionsResponse>,

    @JvmField val defaultValue: String
)

data class FeedbackSubmitModel(
    @JvmField val userId: String,
    @JvmField val role: String,
    @JvmField val answers: List<FeedbackAnswer>
)

data class OptionsResponse(
    @JvmField val optionId: String,

    @JvmField val optionName: String="",

    @JvmField val selected: Boolean
)

data class FeedbackOptionTag(
    @JvmField val questionId: String,
    @JvmField val questionType:String
)



data class CheckBoxOptionTag(
    @JvmField val questionId: String,
    @JvmField val answeredList: MutableList<CheckBoxAnswered>
)

abstract class FeedbackAnswer {
    abstract val questionId: String
    abstract val value: String
}

data class CheckBoxSelected(
    override val questionId: String = "0",
    override val value: String = "",
    @JvmField val options: List<OptionsResponse>
) : FeedbackAnswer()

data class CheckBoxAnswered(
    @JvmField val optionId: String = "0",
    @JvmField val selected: Boolean
)

data class RatingBarItem(
    override val questionId: String = "0",
    override val value: String = ""
) : FeedbackAnswer()

data class TextInputItem(
    override val questionId: String = "0",
    override val value: String = ""
) : FeedbackAnswer()