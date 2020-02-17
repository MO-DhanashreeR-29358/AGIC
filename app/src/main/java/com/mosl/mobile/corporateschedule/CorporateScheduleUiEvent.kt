package com.mosl.mobile.corporateschedule

import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo

sealed class CorporateScheduleUiEvent {
    data class OnMoreClick(
        @JvmField val Rep: List<CorporateScheduleResponseRepresentativeItemInfo>
    ) : CorporateScheduleUiEvent()
}