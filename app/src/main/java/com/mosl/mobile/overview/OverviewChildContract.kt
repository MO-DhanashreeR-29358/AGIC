package com.mosl.mobile.overview

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.overviewpager.OverviewResponseDayItemInfo
import com.mosl.mobile.domain.overviewpager.OverviewResponseRepresntativeInfo
import io.reactivex.Observable

interface OverviewChildContract : BaseView {
    fun renderPage(uiModel: UiModel<List<OverviewResponseDayItemInfo>>)
    fun onTabClicked(): Observable<UiModel<List<OverviewResponseDayItemInfo>>>

    fun onItemClicked(): Observable<OverviewUiEvent.OnMoreClick>
    fun renderItemData(repList: List<OverviewResponseRepresntativeInfo>)
}