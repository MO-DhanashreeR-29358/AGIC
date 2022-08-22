package com.mosl.volunteer.domain.schedule

data class ScheduleResponse(

    @JvmField var data: List<ScheduleResponseItemInfo>?,
    @JvmField val pdfLink: String?,
    @JvmField val message: String,
    @JvmField val notificationPending: Int,
    @JvmField val feedbackPending: Boolean
)