package com.mosl.mobile.corporatepager

import com.mosl.mobile.domain.corporateschedulepager.SearchCorporateScheduleItemInfo

sealed class CorporatePagerUiEvent {
  data class OnSearchClick(@JvmField  val search: SearchCorporateScheduleItemInfo) : CorporatePagerUiEvent()
}