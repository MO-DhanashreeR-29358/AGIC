package com.mosl.mobile.volunteer.domain.panel

data class PanelResponse(
    @JvmField val day1: List<PanelDayResponse>,
    @JvmField val day2: List<PanelDayResponse>,
    @JvmField val day3: List<PanelDayResponse>
) {
    fun PanelDayWiseListData(): List<List<PanelDayResponse>> {

        val panelDayWiseListData = mutableListOf<List<PanelDayResponse>>()

        panelDayWiseListData.apply {

            val sortedList1 = day1.sortedWith(compareBy({ it.representativeStarttime }))
            val sortedList2 = day2.sortedWith(compareBy({ it.representativeStarttime }))
            val sortedList3 = day3.sortedWith(compareBy({ it.representativeStarttime }))

            add(sortedList1)
            add(sortedList2)
            add(sortedList3)
        }
        return panelDayWiseListData

    }
}