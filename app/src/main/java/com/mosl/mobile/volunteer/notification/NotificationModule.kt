package com.mosl.mobile.volunteer.notification

import com.mosl.mobile.volunteer.data.notification.NotificationRepositoryImpl
import com.mosl.mobile.volunteer.domain.notification.NotificationRepository
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