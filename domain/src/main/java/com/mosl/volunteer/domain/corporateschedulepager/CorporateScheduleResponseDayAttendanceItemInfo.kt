package com.mosl.volunteer.domain.corporateschedulepager

data class CorporateScheduleResponseDayAttendanceItemInfo(

    @JvmField val FundName: String,

    @JvmField val representative: List<CorporateScheduleResponseRepresentativeItemInfo>

)