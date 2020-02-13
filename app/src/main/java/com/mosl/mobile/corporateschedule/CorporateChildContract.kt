package com.mosl.mobile.corporateschedule

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo
import io.reactivex.Observable

interface CorporateChildContract : BaseView {

    fun renderPage(uiModel: UiModel<List<CorporateScheduleResponseDayItemInfo>>)
    fun onTabClicked(): Observable<UiModel<List<CorporateScheduleResponseDayItemInfo>>>
    fun onItemClicked(): Observable<CorporateScheduleUiEvent.OnMoreClick>
    fun renderItemData(repList: List<CorporateScheduleResponseRepresentativeItemInfo>)

}