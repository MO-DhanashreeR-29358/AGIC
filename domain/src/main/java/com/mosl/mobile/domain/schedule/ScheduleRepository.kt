package com.mosl.mobile.domain.schedule

import io.reactivex.Observable

interface ScheduleRepository {
    fun getScheduleData(): Observable<ScheduleResponse>
}