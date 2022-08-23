package com.mosl.mobile.volunteer.domain.schedule

data class ScheduleRequestInfo(
    @JvmField val clientID: String,
    @JvmField val schedule: String,
    @JvmField val timestamp: String
)
