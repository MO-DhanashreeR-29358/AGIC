package com.mosl.mobile.domain.overviewpager


data class OverviewResponseDataInfo(
    val day1: List<OverviewResponseDayItemInfo>,
    val day2: List<OverviewResponseDayItemInfo>,
    val day3: List<OverviewResponseDayItemInfo>
){
    fun OverViewDayWiseListData(): List<List<OverviewResponseDayItemInfo>> {

        val panelDayWiseListData = mutableListOf<List<OverviewResponseDayItemInfo>>()

        panelDayWiseListData.apply {

            val sortedList1 = day1.sortedWith(compareBy({ it.startTime }))
            val sortedList2 = day2.sortedWith(compareBy({ it.startTime }))
            val sortedList3 = day3.sortedWith(compareBy({ it.startTime }))

            add(sortedList1)
            add(sortedList2)
            add(sortedList3)
        }
        return panelDayWiseListData

    }
}

data class RmOverviewResponseDataInfo(
    val day1: List<RmOverViewMeetingResponseInfo>,
    val day2: List<RmOverViewMeetingResponseInfo>,
    val day3: List<RmOverViewMeetingResponseInfo>
){
    fun RmOverViewDayWiseListData(): List<List<RmOverViewMeetingResponseInfo>> {

        val panelDayWiseListData = mutableListOf<List<RmOverViewMeetingResponseInfo>>()

        panelDayWiseListData.apply {

            val sortedList1 = day1.sortedWith(compareBy({ it.meetingTime }))
            val sortedList2 = day2.sortedWith(compareBy({ it.meetingTime }))
            val sortedList3 = day3.sortedWith(compareBy({ it.meetingTime}))

            add(sortedList1)
            add(sortedList2)
            add(sortedList3)
        }
        return panelDayWiseListData

    }
}
