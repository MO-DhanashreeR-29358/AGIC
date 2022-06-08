package com.mosl.data.api.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleRequest (
    @JvmField @SerializedName("ClientID")
    val clientid: String,

    @JvmField @SerializedName("schedule")
    val date: String,

    @JvmField @SerializedName("timestamp")
    val time: String

)