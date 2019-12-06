package com.mosl.mobile.domain.schedule

import io.reactivex.Observable

interface ScheduleRepository {
    fun getScheduleData(scheduleInafoa: ScheduleRequestInfo): Observable<ScheduleResponse>
    fun getPdf(): String
    fun setPdf(pdfLink:String?)
}