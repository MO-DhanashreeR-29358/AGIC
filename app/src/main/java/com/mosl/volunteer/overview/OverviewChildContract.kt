package com.mosl.volunteer.overview

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.volunteer.domain.overviewpager.OverviewResponseRepresntativeInfo
import com.mosl.volunteer.domain.overviewpager.RmClientWiseResponseInfo
import com.mosl.volunteer.domain.overviewpager.RmOverViewMeetingResponseInfo
import io.reactivex.Observable

interface OverviewChildContract : BaseView {
    fun renderPage(uiModel: UiModel<List<RmOverViewMeetingResponseInfo>>)
    fun renderPageClientData(uiModel: UiModel<List<RmClientWiseResponseInfo>>)
    fun onTabClicked(): Observable<UiModel<List<RmOverViewMeetingResponseInfo>>>
    fun onTabClickedClientWise(): Observable<UiModel<List<RmClientWiseResponseInfo>>>

    fun onItemClicked(): Observable<OverviewUiEvent.OnMoreClick>
    fun renderItemData(repList: List<OverviewResponseRepresntativeInfo>)
}