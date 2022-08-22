package com.mosl.volunteer.domain.overviewpager

import io.reactivex.Observable

interface OverviewRepository {
    fun getOverviewData(overviewPagerInfo: OverviewRequestInfo): Observable<OverviewResponseDataInfo>
    fun getRmOverviewData(overviewPagerInfo: RmOverviewRequestInfo): Observable<RmOverviewResponseDataInfo>
    fun getRmClientOverviewData(overviewPagerInfo: RmOverviewRequestInfo): Observable<RmClientWiseResponseDataInfo>
}