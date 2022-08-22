package com.mosl.volunteer.panelpager

import com.mosl.volunteer.domain.panel.PanelDayResponse

sealed class PanelTabChangeEvent {
    data class TabClick(
        var panelDayWiseListData: List<PanelDayResponse>
    ) : PanelTabChangeEvent()

    data class Error(val error: Throwable) : PanelTabChangeEvent()

}

