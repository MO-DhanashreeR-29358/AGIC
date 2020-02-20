package com.mosl.mobile.data.notification

import com.mosl.data.api.AGICApi
import com.mosl.data.api.notification.NotificationResponseItem
import com.mosl.mobile.domain.notification.NotificationRepository
import com.mosl.mobile.domain.notification.NotificationRequestInfo
import com.mosl.mobile.domain.notification.NotificationResponse
import io.reactivex.Observable
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : NotificationRepository {

    private val mapper = NotificationMapper

    override fun getNotificationData(notPagerInfo: NotificationRequestInfo): Observable<List<NotificationResponse>> {


        return agicApi.getNotification(mapper.getNotificationRequestMap(notPagerInfo)).map {
            mapper.transformNotification(it)
        }

    }

}


