package com.mosl.mobile.volunteer.data.overviewpager

import com.mosl.data.api.AGICApi
import com.mosl.mobile.volunteer.domain.overviewpager.*
import io.reactivex.Observable
import javax.inject.Inject

class OverviewPagerRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : OverviewRepository {

    private val mapper = OverviewPagerMapper

    override fun getOverviewData(overviewPagerInfo: OverviewRequestInfo): Observable<OverviewResponseDataInfo> {
        return agicApi.getClientIDOverAPI(mapper.getOverviewPagerRequestMap(overviewPagerInfo))
//        return agicApi.getClientIDOverAPI("6554")
            .map {
                mapper.getScheduleMap(it)
            }
    }

    override fun getRmOverviewData(overviewPagerInfo: RmOverviewRequestInfo): Observable<RmOverviewResponseDataInfo> {
        return agicApi.getRMDayWiseDataAPI(mapper.getRmOverviewPagerRequestMap(overviewPagerInfo))
            .map {
                mapper.getRmDayWiseMap(it)
            }
    }

    override fun getRmClientOverviewData(overviewPagerInfo: RmOverviewRequestInfo): Observable<RmClientWiseResponseDataInfo> {
        return agicApi.getRMClientWiseDataAPI(mapper.getRmOverviewPagerRequestMap(overviewPagerInfo))
            .map {
                mapper.getRmClientWiseMap(it)
            }
    }
}