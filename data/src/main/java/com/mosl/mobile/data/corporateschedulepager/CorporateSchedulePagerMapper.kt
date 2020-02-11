package com.mosl.mobile.data.corporateschedulepager

import com.mosl.data.api.corporateschedulepager.*
import com.mosl.mobile.domain.corporateschedulepager.*

object CorporateSchedulePagerMapper {

    fun getScheduleMap(responseList: CorporateScheduleResponseData): CorporateScheduleResponseDataInfo {

        return CorporateScheduleResponseDataInfo(

            day1 = Day(responseList.day1),
            day2 = Day(responseList.day2),
            day3 = Day(responseList.day3)
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
            Over = requestList.Over
        )
    }
}