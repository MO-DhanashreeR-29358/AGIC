package com.mosl.mobile.overview

import com.mosl.mobile.domain.overviewpager.OverviewResponseRepresntativeInfo

sealed class OverviewUiEvent {
  data class OnMoreClick(@JvmField  val Rep: List<OverviewResponseRepresntativeInfo>) : OverviewUiEvent()
}