package com.mosl.volunteer.overview

import com.mosl.volunteer.domain.overviewpager.OverviewResponseRepresntativeInfo

sealed class OverviewUiEvent {
  data class OnMoreClick(@JvmField  val Rep: List<OverviewResponseRepresntativeInfo>) : OverviewUiEvent()
}