package com.mosl.data.api.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleResponseItemRep(

    @JvmField @SerializedName("NAME")
    val name: String,

    @JvmField @SerializedName("DESIGNATION")
    val designation: String

)