package com.mosl.mobile.volunteer.panelpager

import com.mosl.mobile.volunteer.domain.panel.PanelDayResponse

sealed class PanelTabChangeEvent {
    data class TabClick(
        var panelDayWiseListData: List<PanelDayResponse>
    ) : PanelTabChangeEvent()

    data class Error(val error: Throwable) : PanelTabChangeEvent()

}

