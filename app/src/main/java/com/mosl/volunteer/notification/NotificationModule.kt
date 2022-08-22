package com.mosl.volunteer.notification

import com.mosl.volunteer.data.notification.NotificationRepositoryImpl
import com.mosl.volunteer.domain.notification.NotificationRepository
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