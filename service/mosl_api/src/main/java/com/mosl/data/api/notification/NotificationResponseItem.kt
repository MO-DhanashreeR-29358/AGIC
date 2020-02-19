package com.mosl.data.api.notification

import com.google.gson.annotations.SerializedName

data class NotificationResponseItem(
    @JvmField @SerializedName("corporateName")
    val corporateName: String,

    @JvmField @SerializedName("meetingDateTime")
    val meetingDateTime: String,

    @JvmField @SerializedName("previousDateTime")
    val previousDateTime: String?,

    @JvmField @SerializedName("type")
    val type: String,

    @JvmField @SerializedName("message")
    val message: String
)