package com.mosl.mobile.volunteer.domain.notification

import io.reactivex.Observable

interface NotificationRepository {
    fun getNotificationData(notificationRequestInfo: NotificationRequestInfo): Observable<List<NotificationResponse>>
}