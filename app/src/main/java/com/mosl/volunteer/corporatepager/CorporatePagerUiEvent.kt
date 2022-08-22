package com.mosl.volunteer.corporatepager

import com.mosl.volunteer.domain.corporateschedulepager.SearchCorporateScheduleItemInfo

sealed class CorporatePagerUiEvent {
  data class OnSearchClick(@JvmField  val search: SearchCorporateScheduleItemInfo) : CorporatePagerUiEvent()
}