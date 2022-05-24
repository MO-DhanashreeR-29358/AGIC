package com.mosl.mobile.rmclientwise

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.overviewpager.OverviewResponseRepresntativeInfo
import com.mosl.mobile.domain.overviewpager.RmClientWiseResponseInfo
import com.mosl.mobile.domain.overviewpager.RmOverViewMeetingResponseInfo
import io.reactivex.Observable

interface RmClientWiseContract : BaseView {
    fun renderPage(uiModel: UiModel<List<RmClientWiseResponseInfo>>)
    fun onTabClicked(): Observable<UiModel<List<RmClientWiseResponseInfo>>>
}