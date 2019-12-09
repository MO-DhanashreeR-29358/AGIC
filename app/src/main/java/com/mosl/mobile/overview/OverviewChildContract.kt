package com.mosl.mobile.overview

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseDayItemInfo
import com.mosl.mobile.overviewpager.OverviewPagerTabChangeEvent
import io.reactivex.Observable

interface OverviewChildContract : BaseView {
    fun renderPage(uiModel: UiModel<List<OverviewPagerResponseDayItemInfo>>)
    fun onTabClicked(): Observable<OverviewPagerTabChangeEvent.TabClick>
}