package com.mosl.data.api.notification

import com.google.gson.annotations.SerializedName

data class NotificationRequest(
    @JvmField @SerializedName("ClientID")
    val ClientID: String,

    @JvmField @SerializedName("Role")
    val Role: String

)