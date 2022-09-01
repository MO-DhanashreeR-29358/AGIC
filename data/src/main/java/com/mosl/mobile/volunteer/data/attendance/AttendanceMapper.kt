package com.mosl.mobile.volunteer.data.attendance

import com.mosl.data.api.attendance.GetClientMeetingResponse
import com.mosl.data.api.attendance.AllowedStatus
import com.mosl.data.api.attendance.ClientMeetingDetailsModel
import com.mosl.data.api.attendance.ClientStatus
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.getMilliFromDate

object AttendanceMapper {

    fun GetClientMeetingResponse?.mapToClientDataModel(): ClientMeetingDetailsModel {
        val startTime = getMilliFromDate(
            this?.clientDetails?.startTime ?: "0000-00-00T00:00:00",
            AppDateFormats.DF_JSON_DATE_4)

        val endTime = getMilliFromDate(
            this?.clientDetails?.endTime ?: "0000-00-00T00:00:00",
            AppDateFormats.DF_JSON_DATE_4)

        return ClientMeetingDetailsModel(
            userId = this?.clientDetails?.userId.orEmpty(),
            clientId = this?.clientDetails?.clientId ?: Int.MIN_VALUE,
            clientName = this?.clientDetails?.clientName.orEmpty(),
            fundId = this?.clientDetails?.fundId ?: Int.MIN_VALUE,
            fundName = this?.clientDetails?.fundName.orEmpty(),
            companyId = this?.clientDetails?.companyId.orEmpty(),
            companyName = this?.clientDetails?.companyName.orEmpty(),
            status = getClientStatus(this?.clientDetails?.status.orEmpty()),
            meetingId = this?.clientDetails?.meetingId ?: Int.MIN_VALUE,
            startTime = startTime,
            endTime = endTime,
            isAllowed = getAllowedStatus(this?.clientDetails?.isAllowed ?: Int.MIN_VALUE)
        )
    }

    fun getClientStatus(string: String) = when (string) {
        "1" -> ClientStatus.YES
        else -> ClientStatus.NO
    }

    fun getAllowedStatus(value: Int) = when (value) {
        1 -> AllowedStatus.YES
        else -> AllowedStatus.NO
    }
}