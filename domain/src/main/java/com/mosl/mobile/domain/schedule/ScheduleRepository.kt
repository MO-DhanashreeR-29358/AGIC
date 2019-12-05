package com.mosl.mobile.domain.schedule

import io.reactivex.Observable

interface ScheduleRepository {
    fun getScheduleData(scheduleInafoa: ScheduleRequestInfo): Observable<List<ScheduleResponseItemInfo>>
}