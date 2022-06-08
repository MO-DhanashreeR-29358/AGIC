package com.mosl.mobile.notification

import com.mosl.mobile.data.notification.NotificationRepositoryImpl
import com.mosl.mobile.domain.notification.NotificationRepository
import dagger.Module
import dagger.Provides

@Module
class NotificationModule {

    @Provides
    internal fun provideNotificationHistory(fragment: NotificationHistoryFragment): NotificationContract {
        return fragment
    }

    @Provides
    internal fun providerNotificationRepository(notificationRepositoryImpl: NotificationRepositoryImpl): NotificationRepository {
        return notificationRepositoryImpl
    }
}