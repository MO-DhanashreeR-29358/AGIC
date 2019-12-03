package com.mosl.mobile.domain.schedule

data class ScheduleRequestInfo(
    @JvmField val ClientID: String,
    @JvmField val schedule: String,
    @JvmField val timestamp: String
)
