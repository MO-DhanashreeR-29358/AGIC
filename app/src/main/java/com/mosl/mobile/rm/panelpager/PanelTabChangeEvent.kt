package com.mosl.mobile.rm.panelpager

import com.mosl.mobile.domain.panel.PanelDayResponse

sealed class PanelTabChangeEvent {
    data class TabClick(
        var panelDayWiseListData: List<PanelDayResponse>
    ) : PanelTabChangeEvent()

    data class Error(val error: Throwable) : PanelTabChangeEvent()

}

