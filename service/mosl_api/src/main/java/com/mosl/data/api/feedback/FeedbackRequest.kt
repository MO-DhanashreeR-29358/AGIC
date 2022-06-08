package com.mosl.data.api.feedback

import com.google.gson.annotations.SerializedName

data class FeedbackRequest(
    @JvmField @SerializedName("ClientID")
    val ClientID: String,

    @JvmField @SerializedName("Role")
    val Role: String

)