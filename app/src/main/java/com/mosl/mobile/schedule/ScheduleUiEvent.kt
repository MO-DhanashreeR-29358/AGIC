package com.mosl.mobile.schedule

import com.mosl.data.api.schedule.ScheduleResponseItem
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo_Rep

sealed class ScheduleUiEvent {
  data class OnMoreClick(@JvmField  val Rep: List<ScheduleResponseItemInfo_Rep>) : ScheduleUiEvent()
  data class ItemClick(@JvmField  val objScheduleResponseItemInfo: ScheduleResponseItemInfo) : ScheduleUiEvent()
}