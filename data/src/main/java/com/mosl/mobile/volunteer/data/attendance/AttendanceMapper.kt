package com.mosl.mobile.volunteer.data.attendance

import com.mosl.data.api.attendance.GetClientMeetingResponse

import com.mosl.mobile.volunteer.domain.attendance.ClientDetailsModel

object AttendanceMapper {

    fun GetClientMeetingResponse?.mapToClientDataModel(): ClientDetailsModel {
        return ClientDetailsModel(
            userId = this?.clientDetails?.userId.orEmpty(),
            clientId = this?.clientDetails?.clientId ?: Int.MIN_VALUE,
            clientName = this?.clientDetails?.clientName.orEmpty(),
            fundId = this?.clientDetails?.fundId ?: Int.MIN_VALUE,
            fundName = this?.clientDetails?.fundName.orEmpty(),
            companyId = this?.clientDetails?.companyId.orEmpty(),
            companyName = this?.clientDetails?.companyName.orEmpty(),
            status = this?.clientDetails?.status.orEmpty(),
            meetingId = this?.clientDetails?.meetingId ?: Int.MIN_VALUE,
            startTime = this?.clientDetails?.startTime.orEmpty(),
            endTime = this?.clientDetails?.endTime.orEmpty(),
            isAllowed = this?.clientDetails?.isAllowed ?: Int.MIN_VALUE
        )
    }
}
