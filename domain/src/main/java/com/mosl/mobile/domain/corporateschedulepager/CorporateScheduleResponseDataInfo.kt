package com.mosl.mobile.domain.corporateschedulepager


data class CorporateScheduleResponseDataInfo(
    val day1: List<CorporateScheduleResponseDayItemInfo>,
    val day2: List<CorporateScheduleResponseDayItemInfo>,
    val day3: List<CorporateScheduleResponseDayItemInfo>,
    val pdfLink: String?,
    val message: String,
    val notificationPending: Int,
    val feedbackPending: Boolean

){
    fun CorporateScheduleDayWiseListData(): List<List<CorporateScheduleResponseDayItemInfo>> {

        val panelDayWiseListData = mutableListOf<List<CorporateScheduleResponseDayItemInfo>>()

        panelDayWiseListData.apply {

            val sortedList1 = day1.sortedWith(compareBy({ it.shiftTiming }))
            val sortedList2 = day2.sortedWith(compareBy({ it.shiftTiming }))
            val sortedList3 = day3.sortedWith(compareBy({ it.shiftTiming }))

            add(sortedList1)
            add(sortedList2)
            add(sortedList3)
        }
        return panelDayWiseListData

    }
}
