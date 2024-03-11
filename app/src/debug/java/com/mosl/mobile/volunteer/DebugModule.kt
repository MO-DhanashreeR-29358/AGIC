package com.mosl.mobile.volunteer

import android.app.Application
import android.content.Context
import com.mosl.debugview.DebugViewModule
import com.mosl.mobile.volunteer.coredata.DebugApiModule
import com.mosl.mobile.volunteer.coredata.DebugDataModule
import com.mosl.mobile.volunteer.data.CommonDataModule
import com.mosl.mobile.volunteer.service.ServiceModule
import com.mosl.mobile.volunteer.ui.DebugUiModule
import dagger.Binds
import dagger.Module

@Module(includes = [CommonDataModule::class, DebugApiModule::class, DebugDataModule::class,
  DebugViewModule::class, DebugUiModule::class,ServiceModule::class])
abstract class DebugModule {
  @Binds
  internal abstract fun provideApplication(application: MOSLApplication): Application

  @Binds
  internal abstract fun provideContext(application: Application): Context
}