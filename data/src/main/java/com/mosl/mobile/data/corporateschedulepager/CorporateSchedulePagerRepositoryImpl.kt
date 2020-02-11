package com.mosl.mobile.data.corporateschedulepager

import com.mosl.data.api.AGICApi
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleRepository
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleRequestInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDataInfo
import io.reactivex.Observable
import javax.inject.Inject

class CorporateSchedulePagerRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : CorporateScheduleRepository {

    override fun getCorporateScheduleRepository(overviewPagerInfo: CorporateScheduleRequestInfo): Observable<CorporateScheduleResponseDataInfo> {
        return agicApi.getCorporateScheduleAPI(
            mapper.getCorporateSchedulePagerRequestMap(
                overviewPagerInfo
            )
        )
            .map {
                mapper.getScheduleMap(it)
            }
    }

    private val mapper = CorporateSchedulePagerMapper
}