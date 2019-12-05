package com.mosl.mobile.schedule

import com.mosl.app.core.base.BaseView
import com.mosl.mobile.schedule.ScheduleUiEvent.OnMoreClick
import com.mosl.mobile.schedule.ScheduleUiEvent.ItemClick
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.domain.schedule.ScheduleRepresentativeItemData
import io.reactivex.Observable

interface ScheduleContract : BaseView {

    fun renderScheduleData(uiModel: UiModel<List<ScheduleResponseItemInfo>>)

    fun onItemClickedMain(): Observable<ItemClick>
    fun renderItemToCalender(uiModel: ScheduleResponseItemInfo)

    fun onItemClicked(): Observable<OnMoreClick>
    fun renderItemData(repList: List<ScheduleRepresentativeItemData>)

}