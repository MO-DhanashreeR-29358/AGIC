package com.mosl.mobile.panelpager

import com.mosl.mobile.domain.panel.PanelDayResponse

sealed class PanelTabChangeEvent {
    data class TabClick(
        var panelDayWiseListData: List<PanelDayResponse>
    ) : PanelTabChangeEvent()

}

