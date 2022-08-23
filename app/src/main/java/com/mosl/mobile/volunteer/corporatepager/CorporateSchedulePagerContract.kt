package com.mosl.mobile.volunteer.corporatepager

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.volunteer.domain.corporateschedulepager.CorporateScheduleResponseDataInfo
import com.mosl.mobile.volunteer.domain.corporateschedulepager.SearchCorporateScheduleItemInfo
import io.reactivex.Observable

interface CorporateSchedulePagerContract : BaseView {
    fun renderPage(uiModel: UiModel<CorporateScheduleResponseDataInfo>)

    fun onSearchItemClicked(): Observable<CorporatePagerUiEvent.OnSearchClick>
    fun renderSearchItemData(searchCorporateScheduleItemInfo: SearchCorporateScheduleItemInfo)
}