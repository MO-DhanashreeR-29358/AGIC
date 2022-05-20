package com.mosl.mobile.overview

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.overviewpager.OverviewResponseRepresntativeInfo
import com.mosl.mobile.domain.overviewpager.RmOverViewMeetingResponseInfo
import io.reactivex.Observable

interface OverviewChildContract : BaseView {
    fun renderPage(uiModel: UiModel<List<RmOverViewMeetingResponseInfo>>)
    fun onTabClicked(): Observable<UiModel<List<RmOverViewMeetingResponseInfo>>>

    fun onItemClicked(): Observable<OverviewUiEvent.OnMoreClick>
    fun renderItemData(repList: List<OverviewResponseRepresntativeInfo>)
}