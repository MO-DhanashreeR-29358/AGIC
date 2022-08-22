package com.mosl.volunteer

import android.app.Application
import android.content.Context
import com.mosl.volunteer.coredata.ApiModule
import com.mosl.volunteer.coredata.DataModule
import com.mosl.volunteer.data.CommonDataModule
import com.mosl.volunteer.service.ServiceModule
import dagger.Binds
import dagger.Module

@Module(includes = [UiModule::class,ApiModule::class,CommonDataModule::class, DataModule::class,ServiceModule::class])
abstract class ApplicationModule {
  @Binds
  internal abstract fun provideApplication(application: MOSLApplication): Application

  @Binds
  internal abstract fun provideContext(application: Application): Context
}