package com.mosl.mobile.volunteer.panel

import com.mosl.mobile.volunteer.domain.panel.PanelDayResponse


sealed class PanelUiEvent {
    data class ItemClick(@JvmField  val panelDayResponseItem: PanelDayResponse) : PanelUiEvent()
}