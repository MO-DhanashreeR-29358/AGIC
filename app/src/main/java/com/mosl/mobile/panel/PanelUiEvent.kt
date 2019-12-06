package com.mosl.mobile.panel

import com.mosl.mobile.domain.panel.PanelDayResponse


sealed class PanelUiEvent {
    data class ItemClick(@JvmField  val panelDayResponseItem: PanelDayResponse) : PanelUiEvent()
}