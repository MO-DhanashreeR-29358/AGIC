package com.mosl.mobile.volunteer.corporateschedule

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.volunteer.corporatepager.SchedulePagerAction
import com.mosl.mobile.volunteer.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.mosl.mobile.volunteer.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo
import io.reactivex.Observable

interface CorporateChildContract : BaseView {

    fun renderPage(uiModel: UiModel<List<CorporateScheduleResponseDayItemInfo>>, shiftTime: String)
    fun onTabClicked(): Observable<SchedulePagerAction.PagerClick>
    fun onItemClicked(): Observable<CorporateScheduleUiEvent.OnMoreClick>
    fun renderItemData(repList: List<CorporateScheduleResponseRepresentativeItemInfo>)

}