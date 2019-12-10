package com.mosl.mobile.data.overviewpager

import com.mosl.data.api.AGICApi
import com.mosl.mobile.domain.overviewpager.OverviewRepository
import com.mosl.mobile.domain.overviewpager.OverviewRequestInfo
import com.mosl.mobile.domain.overviewpager.OverviewResponseDataInfo
import io.reactivex.Observable
import javax.inject.Inject

class OverviewPagerRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : OverviewRepository {

    private val mapper = OverviewPagerMapper

    override fun getOverviewData(overviewPagerInfo: OverviewRequestInfo): Observable<OverviewResponseDataInfo> {
        return agicApi.getClientIDOverAPI(mapper.getOverviewPagerRequestMap(overviewPagerInfo))
            .map {
                mapper.getScheduleMap(it)
            }
    }
}