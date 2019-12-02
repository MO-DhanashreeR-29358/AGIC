package com.mosl.mobile.schedule

data class ScheduleData(
    @JvmField val scheduleTime: String,
    @JvmField val scheduleDesignation: String,
    @JvmField val scheduleName: String,
    @JvmField val scheduleVenue: String
)
