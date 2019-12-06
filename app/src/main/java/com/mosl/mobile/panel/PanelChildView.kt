package com.mosl.mobile.panel

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.contactrm.CallUiEvent
import com.mosl.mobile.domain.panel.PanelDayResponse
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.panelpager.PanelListDayItemData
import com.mosl.mobile.panelpager.PanelTabChangeEvent
import com.mosl.mobile.schedule.ScheduleUiEvent
import io.reactivex.Observable

interface PanelChildView : BaseView {
    fun panelDayList():ArrayList<PanelListDayItemData>?
    fun renderPage(uiModel: UiModel<List<PanelDayResponse>>)
    fun onTabClicked(): Observable<PanelTabChangeEvent.TabClick>

    fun renderItemToCalender(uiModel: PanelDayResponse)

    fun onItemClicked(): Observable<PanelUiEvent.ItemClick>

}
