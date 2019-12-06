package com.mosl.mobile.data.overviewpager

import com.mosl.data.api.overviewpager.OverviewPagerRequest
import com.mosl.data.api.overviewpager.OverviewPagerResponseItem
import com.mosl.data.api.schedule.ScheduleRequest
import com.mosl.data.api.schedule.ScheduleResponseItem
import com.mosl.mobile.domain.overviewpager.OverviewPagerRequestInfo
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseInfo
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.domain.schedule.ScheduleRepresentativeItemData

object OverviewPagerMapper {


    fun getScheduleMap(responseList: List<OverviewPagerResponseItem>): List<OverviewPagerResponseInfo> {
        return responseList.map {
            OverviewPagerResponseInfo(
                recordNo = it.record_no,
                ReportURL = it.ReportURL,
                companyName = it.company_name,
                roomNo = it.room_no,
                scheduleDate = it.Schedule_Date,
                fundId = it.fund_id,
                startTime = it.start_time,
                endTime = it.end_time,
                natureOfMeeting = it.Nature_of_meeting,
                updateFlag = it.updateflag,
                updateDate = it.UpdateDate
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

