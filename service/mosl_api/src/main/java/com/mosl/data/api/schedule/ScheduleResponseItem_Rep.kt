package com.mosl.data.api.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleResponseItem_Rep(

    @JvmField @SerializedName("NAME")
    val NAME: String,

    @JvmField @SerializedName("DESIGNATION")
    val DESIGNATION: String

)