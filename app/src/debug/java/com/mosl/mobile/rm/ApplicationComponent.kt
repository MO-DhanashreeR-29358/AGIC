package com.mosl.mobile.rm


import com.mosl.debugview.DebugComponent
import com.mosl.mobile.rm.drawermenu.DrawerMenuView
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, DebugModule::class, DebugActivityBuilder::class])
interface   ApplicationComponent : AndroidInjector<MOSLApplicationImpl>, DebugComponent {

  fun inject(target: DrawerMenuView)

  override fun inject(instance: MOSLApplicationImpl)

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: MOSLApplication): Builder

    fun build(): ApplicationComponent
  }

}