package com.mosl.mobile.data.overviewpager

import com.mosl.data.api.overviewpager.OverviewRequest
import com.mosl.data.api.overviewpager.OverviewResponseData
import com.mosl.data.api.overviewpager.OverviewResponseDayItem
import com.mosl.data.api.overviewpager.OverviewResponseRepository
import com.mosl.mobile.domain.overviewpager.OverviewRequestInfo
import com.mosl.mobile.domain.overviewpager.OverviewResponseDataInfo
import com.mosl.mobile.domain.overviewpager.OverviewResponseDayItemInfo
import com.mosl.mobile.domain.overviewpager.OverviewResponseRepresntativeInfo

object OverviewPagerMapper {

    fun getScheduleMap(responseList: OverviewResponseData): OverviewResponseDataInfo {


        return OverviewResponseDataInfo(

            day1 = Day(responseList.Day1),
            day2 = Day(responseList.Day2),
            day3 = Day(responseList.Day3)
        )
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
}