package com.mosl.data.api.corporateschedulepager

import com.google.gson.annotations.SerializedName

data class CorporateScheduleResponseDayAttendanceItem(

    @JvmField @SerializedName("FundName")
    val FundName: String,

    @JvmField @SerializedName("representative")
    val representative: List<CorporateScheduleResponseRepresentativeItem>

)