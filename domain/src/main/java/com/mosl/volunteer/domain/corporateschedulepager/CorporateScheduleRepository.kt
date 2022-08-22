package com.mosl.volunteer.domain.corporateschedulepager

import io.reactivex.Observable

interface CorporateScheduleRepository {
    fun getCorporateScheduleRepository(overviewPagerInfo: CorporateScheduleRequestInfo): Observable<CorporateScheduleResponseDataInfo>
}