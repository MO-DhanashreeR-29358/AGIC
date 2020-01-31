package com.mosl.mobile.notification

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceModule {

  @ContributesAndroidInjector
  abstract fun provideMessageReceiverIntentServiceFactory(): MessageReceiverIntentService

}