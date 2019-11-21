package com.mosl.mobile

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, AndroidSupportInjectionModule::class,
  ActivityBuilder::class])
interface ApplicationComponent : AndroidInjector<MOSLApplicationImpl> {

  abstract fun inject(target: DrawerMenuView)

  override fun inject(instance: MOSLApplicationImpl)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: MOSLApplication): Builder

    fun build(): ApplicationComponent
  }
}