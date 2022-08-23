package com.mosl.mobile.volunteer

import android.app.Application
import android.content.Context
import com.mosl.mobile.volunteer.coredata.ApiModule
import com.mosl.mobile.volunteer.coredata.DataModule
import com.mosl.mobile.volunteer.data.CommonDataModule
import com.mosl.mobile.volunteer.service.ServiceModule
import dagger.Binds
import dagger.Module

@Module(includes = [UiModule::class, ApiModule::class,CommonDataModule::class, DataModule::class,ServiceModule::class])
abstract class ApplicationModule {
  @Binds
  internal abstract fun provideApplication(application: MOSLApplication): Application

  @Binds
  internal abstract fun provideContext(application: Application): Context
}