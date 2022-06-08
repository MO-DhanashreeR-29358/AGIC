package com.mosl.mobile.domain.schedule

data class ScheduleRequestInfo(
    @JvmField val clientID: String,
    @JvmField val schedule: String,
    @JvmField val timestamp: String
)
