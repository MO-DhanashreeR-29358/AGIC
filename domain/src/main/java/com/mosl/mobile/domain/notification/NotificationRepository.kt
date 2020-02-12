package com.mosl.mobile.domain.notification

import io.reactivex.Observable

interface NotificationRepository {
    fun getNotificationData(): Observable<List<NotificationResponse>>
}