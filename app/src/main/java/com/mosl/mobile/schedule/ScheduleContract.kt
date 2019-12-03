package com.mosl.mobile.schedule

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo

interface ScheduleContract : BaseView {

    fun getScheduleRequestInfo(): ScheduleRequestInfo
    fun renderScheduleData(uiModel: UiModel<List<ScheduleResponseItemInfo>>)
    fun hideProgress()
    fun showProgress()
}