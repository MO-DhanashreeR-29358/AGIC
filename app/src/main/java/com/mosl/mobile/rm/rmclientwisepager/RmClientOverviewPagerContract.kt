package com.mosl.mobile.rm.rmclientwisepager

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.overviewpager.RmClientWiseResponseDataInfo
import com.mosl.mobile.domain.overviewpager.RmOverviewResponseDataInfo
import io.reactivex.Observable

interface RmClientOverviewPagerContract : BaseView {
    fun renderClientWiseData(uiModel: UiModel<RmClientWiseResponseDataInfo>)
}