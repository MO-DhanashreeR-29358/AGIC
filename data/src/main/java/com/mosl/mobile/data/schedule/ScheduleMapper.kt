package com.mosl.mobile.data.schedule

import com.mosl.data.api.schedule.ScheduleRequest
import com.mosl.data.api.schedule.ScheduleResponseItem
import com.mosl.mobile.domain.newsletter.NewsLetterResponseInfo
import com.mosl.mobile.domain.newsletter.NewsLetterResponseItemInfo
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo_Rep

object ScheduleMapper {


    fun getScheduleMap(responseList: List<ScheduleResponseItem>): List<ScheduleResponseItemInfo> {
        return responseList.map {
            ScheduleResponseItemInfo(

                Rep = it.Rep.map {
                    ScheduleResponseItemInfo_Rep(
                        NAME = it.NAME,
                        DESIGNATION = it.DESIGNATION
                    )  },

                record_no = it.record_no,
                ReportURL = it.ReportURL,
                company_name = it.company_name,
                room_no = it.room_no,
                Schedule_Date = it.Schedule_Date,
                fund_id = it.fund_id,
                start_time = it.start_time,
                end_time = it.end_time,
                Nature_of_meeting = it.Nature_of_meeting,
                updateflag = it.updateflag,
                UpdateDate = it.UpdateDate
            )
        }
    }

    fun getScheduleRequestMap(requestList: ScheduleRequestInfo): ScheduleRequest {
        return ScheduleRequest(
            clientid = requestList.ClientID,
            date = requestList.schedule,
            time = requestList.timestamp
        )

    }
}

