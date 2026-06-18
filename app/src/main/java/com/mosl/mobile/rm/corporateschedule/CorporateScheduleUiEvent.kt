package com.mosl.mobile.rm.corporateschedule

import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo

sealed class CorporateScheduleUiEvent {
    data class OnMoreClick(
        @JvmField val Rep: List<CorporateScheduleResponseRepresentativeItemInfo>
    ) : CorporateScheduleUiEvent()
}