package com.mosl.mobile.notification

import dagger.Binds
import dagger.Module

@Module
abstract class NotificationModule {

    @Binds
    abstract fun provideNotificationHistory(fragment: NotificationHistoryFragment):NotificationContract

}