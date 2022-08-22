package com.mosl.volunteer.rmclientwise

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.volunteer.domain.overviewpager.OverviewResponseRepresntativeInfo
import com.mosl.volunteer.domain.overviewpager.RmClientWiseResponseInfo
import com.mosl.volunteer.overview.OverviewUiEvent
import io.reactivex.Observable

interface RmClientWiseContract : BaseView {
    fun renderPage(uiModel: UiModel<List<RmClientWiseResponseInfo>>)
    fun onTabClicked(): Observable<UiModel<List<RmClientWiseResponseInfo>>>
    fun onItemClicked(): Observable<OverviewUiEvent.OnMoreClick>
    fun renderItemData(repList: List<OverviewResponseRepresntativeInfo>)
}