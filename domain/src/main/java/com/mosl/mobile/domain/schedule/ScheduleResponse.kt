package com.mosl.mobile.domain.schedule

data class ScheduleResponse(

    @JvmField var Data: List<ScheduleResponseItemInfo>,
    @JvmField val PDFLink: String?
)