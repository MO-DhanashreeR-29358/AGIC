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
//        return getList().map {
//            mapper.transformNotification(it)
//        }
    }

    private fun getList(): Observable<List<NotificationResponseItem>> {
        return Observable.just(
            listOf(
                NotificationResponseItem(
                    corporateName = "Motilal Oswal AMC",
                    meetingDateTime = "2020-02-05T14:00:00",
                    previousDateTime = "",
                    type = "MeetingCreate",
                    message = ""
                ),
                NotificationResponseItem(
                    type = "MeetingUpdated",
                    corporateName = "ICICI AMC",
                    meetingDateTime = "2020-02-05T14:00:00",
                    previousDateTime = "2020-02-05T13:00:00",
                    message = ""
                ),
                NotificationResponseItem(
                    type = "MeetingCancelled",
                    corporateName = "AXIS BANK LTD",
                    meetingDateTime = "2020-02-05T14:00:00",
                    previousDateTime = "",
                    message = ""
                ),
                NotificationResponseItem(
                    type = "",
                    corporateName = "MOTILAL OSWAL",
                    meetingDateTime = "2020-02-05T14:00:00",
                    previousDateTime = "",
                    message = "Please have a look at the app for your live schedule for the conference"
                )

            )
        )
    }
}


