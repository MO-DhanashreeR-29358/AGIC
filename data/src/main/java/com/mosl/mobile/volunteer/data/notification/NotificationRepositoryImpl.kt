package com.mosl.mobile.volunteer.data.notification

import com.mosl.data.api.AGICApi
import com.mosl.mobile.volunteer.domain.notification.NotificationRepository
import com.mosl.mobile.volunteer.domain.notification.NotificationRequestInfo
import com.mosl.mobile.volunteer.domain.notification.NotificationResponse
import io.reactivex.Observable
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : NotificationRepository {

    private val mapper = NotificationMapper

    override fun getNotificationData(notificationRequestInfo: NotificationRequestInfo): Observable<List<NotificationResponse>> {


        return agicApi.getNotification(mapper.getNotificationRequestMap(notificationRequestInfo)).map {
            mapper.transformNotification(it)
        }

    }

}


