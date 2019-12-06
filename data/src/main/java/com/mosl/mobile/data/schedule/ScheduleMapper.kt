package com.mosl.mobile.data.schedule

import com.mosl.data.api.schedule.ScheduleRequest
import com.mosl.data.api.schedule.ScheduleResponseItem
import com.mosl.mobile.domain.schedule.ScheduleRepresentativeItemData
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponse
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo

object ScheduleMapper {


    fun getScheduleMap(responseList: List<ScheduleResponseItem>?, pdf: String?): ScheduleResponse {
        val responseLisaat: List<ScheduleResponseItemInfo> = responseList!!.map {
            ScheduleResponseItemInfo(

                Rep = it.Rep.map {
                    ScheduleRepresentativeItemData(
                        NAME = it.NAME,
                        DESIGNATION = it.DESIGNATION
                    )
                },

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

        return ScheduleResponse(
            Data = responseLisaat,
            PDFLink = pdf
        )
    }

    fun getScheduleRequestMap(requestList: ScheduleRequestInfo): ScheduleRequest {
        return ScheduleRequest(
            clientid = requestList.clientID,
            date = requestList.schedule,
            time = requestList.timestamp
        )

    }
}

