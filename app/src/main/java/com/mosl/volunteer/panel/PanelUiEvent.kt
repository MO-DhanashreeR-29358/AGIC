package com.mosl.volunteer.panel

import com.mosl.volunteer.domain.panel.PanelDayResponse


sealed class PanelUiEvent {
    data class ItemClick(@JvmField  val panelDayResponseItem: PanelDayResponse) : PanelUiEvent()
}