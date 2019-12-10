package com.mosl.mobile.overview

import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseRepositoryInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo

sealed class OverviewUiEvent {
  data class OnMoreClick(@JvmField  val Rep: List<OverviewPagerResponseRepositoryInfo>) : OverviewUiEvent()
}