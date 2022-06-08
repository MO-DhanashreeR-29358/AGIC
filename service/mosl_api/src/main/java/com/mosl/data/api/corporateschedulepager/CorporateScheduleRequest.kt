package com.mosl.data.api.corporateschedulepager

import com.google.gson.annotations.SerializedName

data class CorporateScheduleRequest(
    @JvmField @SerializedName("ClientID")
    val ClientID: String,

    @JvmField @SerializedName("timestamp")
    val timestamp: String

)