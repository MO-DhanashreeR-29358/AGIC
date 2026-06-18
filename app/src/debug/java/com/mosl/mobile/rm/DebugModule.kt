package com.mosl.mobile.rm

import android.app.Application
import android.content.Context
import com.mosl.debugview.DebugViewModule
import com.mosl.mobile.coredata.DebugApiModule
import com.mosl.mobile.coredata.DebugDataModule
import com.mosl.mobile.rm.data.CommonDataModule
import com.mosl.mobile.rm.service.ServiceModule
import com.mosl.mobile.rm.ui.DebugUiModule
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