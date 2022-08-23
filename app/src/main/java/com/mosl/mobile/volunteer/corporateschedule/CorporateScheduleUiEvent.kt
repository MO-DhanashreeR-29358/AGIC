package com.mosl.mobile.volunteer.corporateschedule

import com.mosl.mobile.volunteer.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo

sealed class CorporateScheduleUiEvent {
    data class OnMoreClick(
        @JvmField val Rep: List<CorporateScheduleResponseRepresentativeItemInfo>
    ) : CorporateScheduleUiEvent()
}