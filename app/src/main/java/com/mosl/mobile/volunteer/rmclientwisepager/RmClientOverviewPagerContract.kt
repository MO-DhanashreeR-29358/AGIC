package com.mosl.mobile.volunteer.rmclientwisepager

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.volunteer.domain.overviewpager.RmClientWiseResponseDataInfo

interface RmClientOverviewPagerContract : BaseView {
    fun renderClientWiseData(uiModel: UiModel<RmClientWiseResponseDataInfo>)
}