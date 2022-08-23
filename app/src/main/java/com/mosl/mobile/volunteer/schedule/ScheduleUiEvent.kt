package com.mosl.mobile.volunteer.schedule

import com.mosl.mobile.volunteer.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.volunteer.domain.schedule.ScheduleRepresentativeItemData

sealed class ScheduleUiEvent {
  data class OnMoreClick(@JvmField  val Rep: List<ScheduleRepresentativeItemData>) : ScheduleUiEvent()
  data class ItemClick(@JvmField  val objScheduleResponseItemInfo: ScheduleResponseItemInfo) : ScheduleUiEvent()
}