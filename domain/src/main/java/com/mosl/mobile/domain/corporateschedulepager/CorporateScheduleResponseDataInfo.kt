package com.mosl.mobile.domain.corporateschedulepager


data class CorporateScheduleResponseDataInfo(
    val day1: List<CorporateScheduleResponseDayItemInfo>,
    val day2: List<CorporateScheduleResponseDayItemInfo>,
    val day3: List<CorporateScheduleResponseDayItemInfo>,
    val pdfLink: String?,
    val message: String,
    val notificationPending: Int,
    val feedbackPending: Boolean,
    val data: List<CorporateScheduleResponseDayItemInfo>


){
    fun CorporateScheduleDayWiseListData(): List<List<CorporateScheduleResponseDayItemInfo>> {

        val panelDayWiseListData = mutableListOf<List<CorporateScheduleResponseDayItemInfo>>()

        panelDayWiseListData.apply {

            add(day1)
            add(day2)
            add(day3)
        }
        return panelDayWiseListData

    }
}
