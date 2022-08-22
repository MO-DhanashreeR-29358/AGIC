package com.mosl.volunteer.domain.schedule

import io.reactivex.Observable

interface ScheduleRepository {
    fun getScheduleData(): Observable<ScheduleResponse>
}