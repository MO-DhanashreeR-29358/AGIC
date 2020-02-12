package com.mosl.mobile.data.notification

import com.mosl.data.api.notification.NotificationResponseItem
import com.mosl.mobile.domain.notification.NotificationResponse

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
                message = it.message
            )
        }
    }
}