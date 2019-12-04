package com.mosl.mobile.schedule

import com.mosl.app.core.base.BaseView
import com.mosl.mobile.schedule.ScheduleUiEvent.OnMoreClick
import com.mosl.mobile.schedule.ScheduleUiEvent.ItemClick
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo_Rep
import io.reactivex.Observable

interface ScheduleContract : BaseView {

    fun renderScheduleData(uiModel: UiModel<List<ScheduleResponseItemInfo>>)

    fun onItemClicked(): Observable<OnMoreClick>
    fun renderItemData(uiModel: List<ScheduleResponseItemInfo_Rep>)
    fun hideProgress()
    fun showProgress()
}