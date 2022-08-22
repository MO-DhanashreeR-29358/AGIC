package com.mosl.volunteer.rmclientwisepager

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.volunteer.domain.overviewpager.RmClientWiseResponseDataInfo

interface RmClientOverviewPagerContract : BaseView {
    fun renderClientWiseData(uiModel: UiModel<RmClientWiseResponseDataInfo>)
}