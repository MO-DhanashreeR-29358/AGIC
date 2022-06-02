package com.mosl.mobile.data.overviewpager

import com.mosl.data.api.overviewpager.*
import com.mosl.data.api.overviewpager.ClientWiseMeetingSlot
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
            day3 = if (data.AGICMeeting.size > 2) {
                meetingTimeSlotData(data.AGICMeeting[2].meetingTimeDisplay)
            }else{
                meetingTimeSlotData(emptyList())
            }
        )
    }

    fun getRmClientWiseMap(data: RmClientWiseResponseData): RmClientWiseResponseDataInfo{

        return RmClientWiseResponseDataInfo(
            day1 = fundData(data.AGICMeeting[0].fundList),
            day2 = fundData(data.AGICMeeting[1].fundList),
            day3 = if (data.AGICMeeting.size > 2) {
                fundData(data.AGICMeeting[0].fundList)
            }else{
                fundData(emptyList())
            }

        )
    }

    fun meetingTimeSlotData(it: List<MeetingSlot>) : List<RmOverViewMeetingResponseInfo>{
        return it.map {meetingData ->
            RmOverViewMeetingResponseInfo(
                meetingTime = meetingData.meetingSlotTime,
                meetingList = DayWiseData(meetingData.meeting.sortedWith(compareBy { it.fundName }))
            )
        }
    }

    fun fundData(fundData: List<FundList>): List<RmClientWiseResponseInfo>{
        return fundData.map {
            RmClientWiseResponseInfo(
                fundID = it.fundID,
                FundName = it.FundName,
                FundRepresentative = fundRepresentativeData(it.FundRepresentative.sortedWith(
                    compareBy { it.fundRepresentativeName }))
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
                rep = DayRepo(it.rep)
            )
        }
    }

    fun fundRepresentativeData(fundRepData: List<FundRepresentative>) : List<FundRepresentativeItemInfo>{
        return fundRepData.map {
            FundRepresentativeItemInfo(
                fundRepresentativeName = it.fundRepresentativeName,
                meetingTimeDisplay = clientWiseMeetingSlot(it.meetingTimeDisplay)
            )
        }
    }

    fun clientWiseMeetingSlot(clientWiseResponseData: List<ClientWiseMeetingSlot>): List<ClientWiseMeetingSlotItemInfo>{
        return clientWiseResponseData.map {
            ClientWiseMeetingSlotItemInfo(
                meetingSlotTime = it.meetingSlotTime,
                meeting = clientMeetingItemInfo(it.meeting)
            )
        }
    }

    fun clientMeetingItemInfo(data: List<Meeting>): List<MeetingItemInfo>{
        return data.map {
            MeetingItemInfo(
                empCode = it.empCode?:"",
                name = it.name?:"",
                corporateName = it.corporateName?:"",
                roomNo = it.roomNo?:"",
                contactName1 = it.contactName1?:"",
                startTime = it.startTime?:"",
                natureOfMeeting = it.natureOfMeeting?:"",
                meetingLastModified = it.meetingLastModified?:"",
                rep = CorporateRepo(it.rep)
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

    fun CorporateRepo(it: List<CorporateRepository>): List<OverviewResponseRepresntativeInfo> {
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