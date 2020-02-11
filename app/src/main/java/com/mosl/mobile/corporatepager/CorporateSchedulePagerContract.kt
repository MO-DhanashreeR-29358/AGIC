package com.mosl.mobile.corporatepager

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDataInfo

interface CorporateSchedulePagerContract : BaseView {
    fun renderPage(uiModel: UiModel<CorporateScheduleResponseDataInfo>)
}