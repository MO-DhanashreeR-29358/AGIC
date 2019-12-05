package com.mosl.mobile.data.schedule

import com.mosl.data.api.AGICApi
import com.mosl.mobile.domain.schedule.ScheduleRepository
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import io.reactivex.Observable
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : ScheduleRepository {

    private val mapper = ScheduleMapper


    override fun getScheduleData(scheduleInafoa: ScheduleRequestInfo): Observable<List<ScheduleResponseItemInfo>> {

        return agicApi.getScheduleDataAPI(mapper.getScheduleRequestMap(scheduleInafoa))
            .map {
                mapper.getScheduleMap(it)
            }
    }
}