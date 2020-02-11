package com.mosl.data.api.corporateschedulepager

import com.google.gson.annotations.SerializedName

data class CorporateScheduleResponseDayItem(

    @JvmField @SerializedName("shiftTiming")
    val shiftTiming: String,

    @JvmField @SerializedName("roomNumber")
    val roomNumber: String,

    @JvmField @SerializedName("Attendees")
    val Attendees: List<CorporateScheduleResponseDayAttendanceItem>,

    @JvmField @SerializedName("natureOfMeeting")
    val natureOfMeeting: String,

    @JvmField @SerializedName("day")
    val day: String
)