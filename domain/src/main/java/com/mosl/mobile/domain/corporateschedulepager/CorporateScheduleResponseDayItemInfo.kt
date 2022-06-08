package com.mosl.mobile.domain.corporateschedulepager

data class CorporateScheduleResponseDayItemInfo(

    @JvmField val shiftTiming: String,

    @JvmField val roomNumber: String,

    @JvmField val Attendees: List<CorporateScheduleResponseDayAttendanceItemInfo>,

    @JvmField val natureOfMeeting: String,

    @JvmField val day: String,
    @JvmField val shiftEndTime: String
)