package com.mosl.mobile.domain.notification

import io.reactivex.Observable

interface NotificationRepository {
    fun getNotificationData(overviewPagerInfo: NotificationRequestInfo): Observable<List<NotificationResponse>>
}