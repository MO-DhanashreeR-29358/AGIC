package com.mosl.mobile.data.overviewpager

import android.util.Log
import com.mosl.data.api.overviewpager.OverviewPagerRequest
import com.mosl.data.api.overviewpager.OverviewPagerResponseData
import com.mosl.data.api.overviewpager.OverviewPagerResponseDayItem
import com.mosl.data.api.overviewpager.OverviewPagerResponseRepository
import com.mosl.mobile.domain.overviewpager.OverviewPagerRequestInfo
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseDataInfo
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseDayItemInfo
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseRepositoryInfo

object OverviewPagerMapper {

    fun getScheduleMap(responseList: OverviewPagerResponseData): OverviewPagerResponseDataInfo {

        Log.d("DATA", responseList.toString())

        return OverviewPagerResponseDataInfo(

            Day1 = Day(responseList.Day1),
            Day2 = Day(responseList.Day2),
            Day3 = Day(responseList.Day3)
        )
    }

    fun Day(it: List<OverviewPagerResponseDayItem>): List<OverviewPagerResponseDayItemInfo> {
        return it.map {
            OverviewPagerResponseDayItemInfo(

                Day = it.Day,
                scheduled_date = it.scheduled_date,
                corporate_Name = it.corporate_Name,
                start_time = it.start_time,
                end_time = it.end_time,
                dayid = it.dayid,
                Nature_of_meeting = it.Nature_of_meeting,
                Rep = DayRepo(it.Rep),
                CompanyName = it.CompanyName,
                CRepresentiveName = it.CRepresentiveName,
                PanelImagePath = it.PanelImagePath,
                SpeakerIntroduction = it.SpeakerIntroduction,
                Designation = it.Designation,
                RepresentativeDate = it.RepresentativeDate,
                RepresentativeStarttime = it.RepresentativeStarttime,
                RepresentativeEndtime = it.RepresentativeEndtime
            )
        }
    }

    fun DayRepo(it: List<OverviewPagerResponseRepository>): List<OverviewPagerResponseRepositoryInfo> {
        return it.map {
            OverviewPagerResponseRepositoryInfo(
                NAME = it.NAME,
                DESIGNATION = it.DESIGNATION
            )
        }
    }


    fun getOverviewPagerRequestMap(requestList: OverviewPagerRequestInfo): OverviewPagerRequest {
        return OverviewPagerRequest(
            ClientID = requestList.ClientID,
            Over = requestList.Over
        )
    }
}