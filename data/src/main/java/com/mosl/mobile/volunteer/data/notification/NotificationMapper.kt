package com.mosl.mobile.volunteer.data.notification

import com.mosl.data.api.notification.NotificationRequest
import com.mosl.data.api.notification.NotificationResponseItem
import com.mosl.mobile.volunteer.domain.notification.NotificationRequestInfo
import com.mosl.mobile.volunteer.domain.notification.NotificationResponse

object NotificationMapper {
    fun transformNotification(
        responseList: List<NotificationResponseItem>?
    ): List<NotificationResponse> {
        return responseList!!.map {
            NotificationResponse(
                corporateName = it.corporateName,
                meetingDateTime = it.meetingDateTime,
                previousDateTime = it.previousDateTime,
                type = it.type,
                message = it.message,
                clientList = it.clientList
            )
        }
    }

    fun getNotificationRequestMap(requestList: NotificationRequestInfo): NotificationRequest {
        return NotificationRequest(
            ClientID = requestList.ClientID,
            Role = requestList.Role
        )
    }

}