package com.mosl.mobile.data.overviewpager

import com.mosl.data.api.AGICApi
import com.mosl.mobile.domain.overviewpager.OverviewPagerRepository
import com.mosl.mobile.domain.overviewpager.OverviewPagerRequestInfo
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseDataInfo
import io.reactivex.Observable
import javax.inject.Inject

class OverviewPagerRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : OverviewPagerRepository {

    private val mapper = OverviewPagerMapper

    override fun getOverviewData(overviewPagerInfo: OverviewPagerRequestInfo): Observable<OverviewPagerResponseDataInfo> {
        return agicApi.getClientIDOverAPI(mapper.getOverviewPagerRequestMap(overviewPagerInfo))
            .map {
                mapper.getScheduleMap(it)
            }
    }
}