package com.mosl.volunteer.overviewpager

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.volunteer.domain.overviewpager.RmClientWiseResponseDataInfo
import com.mosl.volunteer.domain.overviewpager.RmOverviewResponseDataInfo
import io.reactivex.Observable

interface OverviewPagerContract : BaseView {

    fun renderScheduleData(uiModel: UiModel<RmOverviewResponseDataInfo>)
    fun renderClientWiseData(uiModel: UiModel<RmClientWiseResponseDataInfo>)
    fun onClientWiseClicked(): Observable<Unit>
    fun onDayWiseClicked(): Observable<Unit>
}