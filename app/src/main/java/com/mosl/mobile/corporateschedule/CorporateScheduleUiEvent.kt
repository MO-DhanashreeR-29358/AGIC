package com.mosl.mobile.corporateschedule

import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo

sealed class CorporateScheduleUiEvent {
  data class OnMoreClick(@JvmField  val Rep: List<CorporateScheduleResponseDayItemInfo>) : CorporateScheduleUiEvent()
}