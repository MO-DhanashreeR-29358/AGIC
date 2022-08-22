package com.mosl.volunteer.corporatepager

import com.mosl.commons.data.UiModel
import com.mosl.volunteer.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo

sealed class SchedulePagerAction {

    data class PagerClick(
        @JvmField val uiModel: UiModel<List<CorporateScheduleResponseDayItemInfo>>,
        @JvmField val shiftTiming: String = ""
    ) : SchedulePagerAction()
}