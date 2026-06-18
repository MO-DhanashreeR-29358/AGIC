package com.mosl.mobile.rm.panel

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.rm.contactrm.CallUiEvent
import com.mosl.mobile.domain.panel.PanelDayResponse
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.rm.panelpager.PanelListDayItemData
import com.mosl.mobile.rm.panelpager.PanelTabChangeEvent
import com.mosl.mobile.rm.schedule.ScheduleUiEvent
import io.reactivex.Observable

interface PanelChildView : BaseView {
    fun panelDayList():ArrayList<PanelListDayItemData>?
    fun renderPage(uiModel: UiModel<List<PanelDayResponse>>)
    fun onTabClicked(): Observable<UiModel<List<PanelDayResponse>>>

    fun renderItemToCalender(panelDayResponse: PanelDayResponse)

    fun onItemClicked(): Observable<PanelUiEvent.ItemClick>

}
