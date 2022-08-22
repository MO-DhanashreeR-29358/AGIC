package com.mosl.volunteer

import android.app.Application
import android.content.Context
import com.mosl.debugview.DebugViewModule
import com.mosl.volunteer.coredata.DebugApiModule
import com.mosl.volunteer.coredata.DebugDataModule
import com.mosl.volunteer.data.CommonDataModule
import com.mosl.volunteer.service.ServiceModule
import com.mosl.volunteer.ui.DebugUiModule
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