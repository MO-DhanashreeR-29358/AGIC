package com.mosl.mobile.rm.panel

import com.mosl.mobile.domain.panel.PanelDayResponse


sealed class PanelUiEvent {
    data class ItemClick(@JvmField  val panelDayResponseItem: PanelDayResponse) : PanelUiEvent()
}