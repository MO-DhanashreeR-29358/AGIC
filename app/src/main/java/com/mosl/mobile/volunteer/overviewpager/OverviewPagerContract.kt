package com.mosl.mobile.volunteer.overviewpager

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.volunteer.domain.overviewpager.RmClientWiseResponseDataInfo
import com.mosl.mobile.volunteer.domain.overviewpager.RmOverviewResponseDataInfo
import io.reactivex.Observable

interface OverviewPagerContract : BaseView {

    fun renderScheduleData(uiModel: UiModel<RmOverviewResponseDataInfo>)
    fun renderClientWiseData(uiModel: UiModel<RmClientWiseResponseDataInfo>)
    fun onClientWiseClicked(): Observable<Unit>
    fun onDayWiseClicked(): Observable<Unit>
}