package com.mosl.mobile.volunteer.attendance.attendancedetails

import com.mosl.data.api.attendance.ClientDetails
import com.mosl.mobile.volunteer.domain.attendance.ClientDetailsModel

fun ClientDetailsModel?.mapToClientDetails(): ClientDetails {
    return ClientDetails(
        userId = this?.userId.orEmpty(),
        clientId = this?.clientId ?: Int.MIN_VALUE,
        clientName = this?.clientName.orEmpty(),
        fundId = this?.fundId ?: Int.MIN_VALUE,
        fundName = this?.fundName.orEmpty(),
        companyId = this?.companyId.orEmpty(),
        companyName = this?.companyName.orEmpty(),
        status = this?.status.orEmpty(),
        meetingId = this?.meetingId ?: Int.MIN_VALUE,
        startTime = this?.startTime.orEmpty(),
        endTime = this?.endTime.orEmpty(),
        isAllowed = this?.isAllowed ?: Int.MIN_VALUE,
        message = this?.message.orEmpty()
    )
}