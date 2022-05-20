package com.mosl.mobile.overviewpager

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.overviewpager.RmOverviewResponseDataInfo

interface OverviewPagerContract : BaseView {

    fun renderScheduleData(uiModel: UiModel<RmOverviewResponseDataInfo>)

}