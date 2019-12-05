package com.mosl.mobile.domain.schedule

data class ScheduleResponseItemInfo(
    val Rep: List<ScheduleRepresentativeItemData>,
    val recordNo: String,
    val ReportURL: List<String>,
    val companyName: String,
    val roomNo: String,
    val scheduleDate: String,
    val fundId: Int,
    val startTime: String,
    val endTime: String,
    val natureOfMeeting: String,
    val updateFlag: String,
    val updateDate: String

)
