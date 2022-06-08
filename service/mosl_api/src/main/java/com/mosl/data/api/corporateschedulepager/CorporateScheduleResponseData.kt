package com.mosl.data.api.corporateschedulepager

import com.google.gson.annotations.SerializedName


data class CorporateScheduleResponseData(

    @JvmField @SerializedName("Day1")
    val day1: List<CorporateScheduleResponseDayItem>,

    @JvmField @SerializedName("Day2")
    val day2: List<CorporateScheduleResponseDayItem>,

    @JvmField @SerializedName("Day3")
    val day3: List<CorporateScheduleResponseDayItem>,


    @JvmField val pdfLink: String?,
    @JvmField val message: String,
    @JvmField val notificationPending: String,
    @JvmField val feedbackPending: String
)