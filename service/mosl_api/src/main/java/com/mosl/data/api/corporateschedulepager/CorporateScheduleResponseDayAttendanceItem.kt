package com.mosl.data.api.corporateschedulepager

data class CorporateScheduleResponseDayAttendanceItem(

    @JvmField val FundName: String,

    @JvmField val representative: List<CorporateScheduleResponseRepresentativeItem>

)