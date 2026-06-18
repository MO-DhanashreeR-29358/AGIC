package com.mosl.mobile.rm.schedule

import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.domain.schedule.ScheduleRepresentativeItemData

sealed class ScheduleUiEvent {
  data class OnMoreClick(@JvmField  val Rep: List<ScheduleRepresentativeItemData>) : ScheduleUiEvent()
  data class ItemClick(@JvmField  val objScheduleResponseItemInfo: ScheduleResponseItemInfo) : ScheduleUiEvent()
}