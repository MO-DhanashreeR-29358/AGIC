package com.mosl.data.api.corporateschedulepager

import com.google.gson.annotations.SerializedName

data class CorporateScheduleResponseRepresentativeItem(

    @JvmField @SerializedName("name")
    val name: String,

    @JvmField @SerializedName("designation")
    val designation: String
)