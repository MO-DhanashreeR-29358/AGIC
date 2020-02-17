package com.mosl.mobile.corporatepager

import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo

sealed class SchedulePagerAction {

    data class PagerClick(
        @JvmField val uiModel: UiModel<List<CorporateScheduleResponseDayItemInfo>>,
        @JvmField val shiftTiming: String = ""
    ) : SchedulePagerAction()
}