package com.mosl.data.api.feedback

import com.google.gson.annotations.SerializedName

data class FeedbackResponseItem(

    @JvmField @SerializedName("questionId")
    val questionId: String,

    @JvmField @SerializedName("questionTitle")
    val questionTitle: String,

    @JvmField @SerializedName("questionType")
    val questionType: String,

    @JvmField @SerializedName("options")
    val options: List<OptionsResponseItem>,

    @JvmField @SerializedName("defaultValue")
    val defaultValue: String
)