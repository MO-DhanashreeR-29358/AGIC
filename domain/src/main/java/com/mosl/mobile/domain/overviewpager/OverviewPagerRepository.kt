package com.mosl.mobile.domain.overviewpager

import io.reactivex.Observable

interface OverviewPagerRepository {
    fun getOverviewData(overviewPagerInfo: OverviewPagerRequestInfo): Observable<List<OverviewPagerResponseInfo>>
}