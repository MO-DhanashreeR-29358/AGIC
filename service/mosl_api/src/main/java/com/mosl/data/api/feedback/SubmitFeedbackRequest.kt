package com.mosl.data.api.feedback

import com.google.gson.annotations.SerializedName

data class SubmitFeedbackRequest(

    @JvmField @SerializedName("userId")
    val userId: String,
    @JvmField @SerializedName("role")
    val role: String,
    @JvmField @SerializedName("answers")
    val answers: List<FeedbackAnswerRequest>

)