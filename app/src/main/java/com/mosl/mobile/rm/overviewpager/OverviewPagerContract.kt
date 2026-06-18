package com.mosl.mobile.rm.overviewpager

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.overviewpager.RmClientWiseResponseDataInfo
import com.mosl.mobile.domain.overviewpager.RmOverviewResponseDataInfo
import io.reactivex.Observable

interface OverviewPagerContract : BaseView {

    fun renderScheduleData(uiModel: UiModel<RmOverviewResponseDataInfo>)
    fun renderClientWiseData(uiModel: UiModel<RmClientWiseResponseDataInfo>)
    fun onClientWiseClicked(): Observable<Unit>
    fun onDayWiseClicked(): Observable<Unit>
}