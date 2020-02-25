package com.mosl.data.api.feedback

import com.google.gson.annotations.SerializedName

data class FeedbackAnswerRequest(
    @JvmField @SerializedName("value")
    val value: String,
    @JvmField @SerializedName("questionId")
    val questionId: String,
    @JvmField @SerializedName("options")
    val options: List<OptionsSubmitItem>
)