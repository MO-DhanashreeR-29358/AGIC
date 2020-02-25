package com.mosl.mobile.data.schedule

import com.mosl.data.api.schedule.ScheduleRequest
import com.mosl.data.api.schedule.ScheduleResponseItem
import com.mosl.mobile.domain.schedule.ScheduleRepresentativeItemData
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponse
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo

object ScheduleMapper {

    fun getScheduleMap(scheduleResponseItemList:
                       List<ScheduleResponseItem>?, pdf: String?,message:String,
                       feedbackPending:Boolean,notificationPending:Int): ScheduleResponse {

        if(scheduleResponseItemList.isNullOrEmpty()) return ScheduleResponse(emptyList(),pdf,message,notificationPending,feedbackPending)

        val responseList= scheduleResponseItemList
            .map { ScheduleResponseItemInfo(
                Rep = it.Rep.map {
                    ScheduleRepresentativeItemData(
                        name = it.name,
                        designation = it.designation
                    )
                },

                recordNo = it.recordNo,
                ReportURL = it.ReportURL,
                companyName = it.companyName,
                roomNo = it.roomNo,
                scheduleDate = it.ScheduleDate,
                fundId = it.fundId,
                startTime = it.startTime,
                endTime = it.endTime,
                natureOfMeeting = it.NatureOfMeeting,
                updateFlag = it.updateFlag,
                updateDate = it.UpdateDate
            )
        }

        return ScheduleResponse(
            data = responseList,
            pdfLink = pdf,
            message = message,
            feedbackPending = feedbackPending,
            notificationPending = notificationPending
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

