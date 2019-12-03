package com.mosl.mobile.domain.schedule

data class ScheduleResponseItemInfo(
    val Rep: List<ScheduleResponseItemInfo_Rep>,
    val record_no: String,
    val ReportURL: List<String>,
    val company_name: String,
    val room_no: String,
    val Schedule_Date: String,
    val fund_id: Int,
    val start_time: String,
    val end_time: String,
    val Nature_of_meeting: String,
    val updateflag: String,
    val UpdateDate: String

)
