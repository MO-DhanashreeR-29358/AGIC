package com.mosl.mobile.panel

import com.mosl.app.core.base.BaseView
import com.mosl.mobile.panelpager.PanelListDayItemData

interface PanelChildView : BaseView {
    fun panelDayList():ArrayList<PanelListDayItemData>?
    fun renderPage(panelListDayData: List<PanelListDayItemData>?)
}
