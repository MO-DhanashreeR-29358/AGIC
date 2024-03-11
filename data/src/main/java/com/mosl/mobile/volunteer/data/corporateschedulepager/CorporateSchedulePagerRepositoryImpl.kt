package com.mosl.mobile.volunteer.data.corporateschedulepager

import com.mosl.data.api.AGICApi
import com.mosl.mobile.volunteer.domain.corporateschedulepager.CorporateScheduleRepository
import com.mosl.mobile.volunteer.domain.corporateschedulepager.CorporateScheduleRequestInfo
import com.mosl.mobile.volunteer.domain.corporateschedulepager.CorporateScheduleResponseDataInfo
import io.reactivex.Observable
import javax.inject.Inject

class CorporateSchedulePagerRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : CorporateScheduleRepository {

    private val mapper = CorporateSchedulePagerMapper


    override fun getCorporateScheduleRepository(overviewPagerInfo: CorporateScheduleRequestInfo): Observable<CorporateScheduleResponseDataInfo> {

        return agicApi.getCorporateScheduleAPI(
            mapper.getCorporateSchedulePagerRequestMap(
                overviewPagerInfo
            )
        )
            .map {
                mapper.getScheduleMap(
                    it.data,
                    it.pdf,
                    it.message,
                    it.notificationPending,
                    it.feedbackPending
                )
            }
    }
}