package com.mosl.mobile.domain.corporateschedulepager

import io.reactivex.Observable

interface CorporateScheduleRepository {
    fun getCorporateScheduleRepository(overviewPagerInfo: CorporateScheduleRequestInfo): Observable<CorporateScheduleResponseDataInfo>
}