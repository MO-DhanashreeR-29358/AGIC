package com.mosl.volunteer.corporateschedule

import com.mosl.volunteer.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo

sealed class CorporateScheduleUiEvent {
    data class OnMoreClick(
        @JvmField val Rep: List<CorporateScheduleResponseRepresentativeItemInfo>
    ) : CorporateScheduleUiEvent()
}