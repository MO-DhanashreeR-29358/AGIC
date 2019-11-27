package com.mosl.mobile

import android.app.Application
import android.content.Context
import com.mosl.mobile.coredata.ApiModule
import com.mosl.mobile.data.CommonDataModule
import dagger.Binds
import dagger.Module

@Module(includes = [ApiModule::class,CommonDataModule::class])
abstract class ApplicationModule {
  @Binds
  internal abstract fun provideApplication(application: MOSLApplication): Application

  @Binds
  internal abstract fun provideContext(application: Application): Context
}