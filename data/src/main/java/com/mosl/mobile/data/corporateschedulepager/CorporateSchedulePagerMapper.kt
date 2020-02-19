package com.mosl.mobile.data.corporateschedulepager

import com.mosl.data.api.corporateschedulepager.CorporateScheduleRequest
import com.mosl.data.api.corporateschedulepager.CorporateScheduleResponseDayAttendanceItem
import com.mosl.data.api.corporateschedulepager.CorporateScheduleResponseDayItem
import com.mosl.data.api.corporateschedulepager.CorporateScheduleResponseRepresentativeItem
import com.mosl.mobile.domain.corporateschedulepager.*

object CorporateSchedulePagerMapper {

    fun getScheduleMap(
        responseList: List<CorporateScheduleResponseDayItem>?, pdfLink: String, message: String,
        notificationPending: Int, feedbackPending: Boolean
    ): CorporateScheduleResponseDataInfo {
        if (responseList.isNullOrEmpty())
            return CorporateScheduleResponseDataInfo(
                emptyList(), emptyList(), emptyList(), pdfLink, message,
                notificationPending, feedbackPending
            )

        return CorporateScheduleResponseDataInfo(
            day1 = Day(responseList.filter { it.day.equals("Day1", ignoreCase = true) }),
            day2 = Day(responseList.filter { it.day.equals("Day2", ignoreCase = true) }),
            day3 = Day(responseList.filter { it.day.equals("Day3", ignoreCase = true) }),
            pdfLink = pdfLink,
            message = message,
            notificationPending = notificationPending,
            feedbackPending = feedbackPending
        )

    }

    fun Day(it: List<CorporateScheduleResponseDayItem>): List<CorporateScheduleResponseDayItemInfo> {
        return it.map {
            CorporateScheduleResponseDayItemInfo(

                day = it.day,
                shiftTiming = it.shiftTiming,
                roomNumber = it.roomNumber,
                natureOfMeeting = it.natureOfMeeting,
                Attendees = DayAttendance(it.Attendees)

            )
        }
    }

    fun DayAttendance(it: List<CorporateScheduleResponseDayAttendanceItem>): List<CorporateScheduleResponseDayAttendanceItemInfo> {
        return it.map {
            CorporateScheduleResponseDayAttendanceItemInfo(
                FundName = it.FundName,
                representative = Representative(it.representative)
            )
        }
    }


    fun Representative(it: List<CorporateScheduleResponseRepresentativeItem>): List<CorporateScheduleResponseRepresentativeItemInfo> {
        return it.map {
            CorporateScheduleResponseRepresentativeItemInfo(
                name = it.name,
                designation = it.designation
            )
        }
    }


    fun getCorporateSchedulePagerRequestMap(requestList: CorporateScheduleRequestInfo): CorporateScheduleRequest {
        return CorporateScheduleRequest(
            ClientID = requestList.ClientID,
            timestamp = requestList.timestamp
        )
    }
}