package com.mosl.mobile.domain.overviewpager

import io.reactivex.Observable

interface OverviewRepository {
    fun getOverviewData(overviewPagerInfo: OverviewRequestInfo): Observable<OverviewResponseDataInfo>
}