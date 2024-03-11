package com.mosl.mobile.volunteer.service

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceModule {

  @ContributesAndroidInjector
  abstract fun provideMessageReceiverIntentServiceFactory(): MessageReceiverIntentService

}