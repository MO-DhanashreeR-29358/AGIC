package com.mosl.mobile.volunteer.overview

import com.mosl.mobile.volunteer.domain.overviewpager.OverviewResponseRepresntativeInfo

sealed class OverviewUiEvent {
  data class OnMoreClick(@JvmField  val Rep: List<OverviewResponseRepresntativeInfo>) : OverviewUiEvent()
}