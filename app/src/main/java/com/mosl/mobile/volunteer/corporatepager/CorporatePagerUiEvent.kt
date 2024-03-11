package com.mosl.mobile.volunteer.corporatepager

import com.mosl.mobile.volunteer.domain.corporateschedulepager.SearchCorporateScheduleItemInfo

sealed class CorporatePagerUiEvent {
  data class OnSearchClick(@JvmField  val search: SearchCorporateScheduleItemInfo) : CorporatePagerUiEvent()
}