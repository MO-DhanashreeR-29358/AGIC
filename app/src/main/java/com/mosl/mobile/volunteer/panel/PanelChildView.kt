package com.mosl.mobile.volunteer.panel

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.volunteer.domain.panel.PanelDayResponse
import com.mosl.mobile.volunteer.panelpager.PanelListDayItemData
import io.reactivex.Observable

interface PanelChildView : BaseView {
    fun panelDayList():ArrayList<PanelListDayItemData>?
    fun renderPage(uiModel: UiModel<List<PanelDayResponse>>)
    fun onTabClicked(): Observable<UiModel<List<PanelDayResponse>>>

    fun renderItemToCalender(panelDayResponse: PanelDayResponse)

    fun onItemClicked(): Observable<PanelUiEvent.ItemClick>

}
