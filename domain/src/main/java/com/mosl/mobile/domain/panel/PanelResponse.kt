package com.mosl.mobile.domain.panel

data class PanelResponse(
    @JvmField val day1: List<PanelDayResponse>,
    @JvmField val day2: List<PanelDayResponse>,
    @JvmField val day3: List<PanelDayResponse>
) {
    fun PanelDayWiseListData(): List<List<PanelDayResponse>> {

        val panelDayWiseListData = mutableListOf<List<PanelDayResponse>>()

        panelDayWiseListData.apply {
            add(day1)
            add(day2)
            add(day3)
        }
        return panelDayWiseListData

    }
}