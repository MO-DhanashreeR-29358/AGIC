package com.mosl.mobile.data.overviewpager

import com.mosl.data.api.overviewpager.*
import com.mosl.mobile.domain.overviewpager.*

object OverviewPagerMapper {

    fun getScheduleMap(responseList: OverviewResponseData): OverviewResponseDataInfo {


        return OverviewResponseDataInfo(

            day1 = Day(responseList.Day1),
            day2 = Day(responseList.Day2),
            day3 = Day(responseList.Day3)
        )
    }

    fun getRmDayWiseMap(data: RmOverViewResponseData): RmOverviewResponseDataInfo{

        return RmOverviewResponseDataInfo(
            day1 = meetingTimeSlotData(data.AGICMeeting[0].meetingTimeDisplay),
            day2 = meetingTimeSlotData(data.AGICMeeting[1].meetingTimeDisplay),
            day3 = meetingTimeSlotData(data.AGICMeeting[2].meetingTimeDisplay)
        )
    }

    fun meetingTimeSlotData(it: List<MeetingSlot>) : List<RmOverViewMeetingResponseInfo>{
        return it.map {
            RmOverViewMeetingResponseInfo(
                meetingTime = it.meetingSlotTime,
                meetingList = DayWiseData(it.meeting)
            )
        }
    }

    fun DayWiseData(it: List<MeetingTimeListItem>): List<RmOverviewResponseDayItemInfo>{
        return it.map {
            RmOverviewResponseDayItemInfo(
                empCode = it.empCode?:"",
                name = it.name?:"",
                fundId = it.fundId?:"",
                fundName = it.fundName?:"",
                clientId = it.clientId?:"",
                clientName = it.clientName?:"",
                companyName = it.companyName?:"",
                roomNo = it.roomNo?:"",
                contactName1 = it.contactName1?:"",
                startTime = it.startTime?:"",
                natureOfMeeting = it.natureOfMeeting?:"",
            )
        }
    }

    fun Day(it: List<OverviewResponseDayItem>): List<OverviewResponseDayItemInfo> {
        return it.map {
            OverviewResponseDayItemInfo(

                day = it.day,
                scheduledDate = it.scheduledDate,
                corporateName = it.corporateName,
                startTime = it.startTime,
                endTime = it.endTime,
                dayid = it.dayid,
                natureOfMeeting = it.natureOfMeeting,
                rep = DayRepo(it.rep),
                companyName = it.companyName,
                cRepresentiveName = it.cRepresentiveName,
                panelImagePath = it.panelImagePath,
                speakerIntroduction = it.speakerIntroduction,
                designation = it.designation,
                representativeDate = it.representativeDate,
                representativeStartTime = it.representativeStartTime,
                representativeEndTime = it.representativeEndTime
            )
        }
    }

    fun DayRepo(it: List<OverviewResponseRepository>): List<OverviewResponseRepresntativeInfo> {
        return it.map {
            OverviewResponseRepresntativeInfo(
                name = it.name,
                designation = it.designation
            )
        }
    }


    fun getOverviewPagerRequestMap(requestList: OverviewRequestInfo): OverviewRequest {
        return OverviewRequest(
            ClientID = requestList.ClientID,
            Over = requestList.Over
        )
    }

    fun getRmOverviewPagerRequestMap(requestList: RmOverviewRequestInfo): RMDayWiseDataRequest {
        return RMDayWiseDataRequest(
            ClientID = requestList.ClientID,
        )
    }
}