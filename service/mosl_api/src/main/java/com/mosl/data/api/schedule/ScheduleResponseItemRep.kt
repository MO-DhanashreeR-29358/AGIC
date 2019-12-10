package com.mosl.data.api.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleResponseItemRep(

    @JvmField @SerializedName("NAME")
    val NAME: String,

    @JvmField @SerializedName("DESIGNATION")
    val DESIGNATION: String

)