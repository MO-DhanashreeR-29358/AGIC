package com.mosl.mobile.rm

import com.mosl.annotations.ActivityScoped
import com.mosl.mobile.rm.core.CommonActivityModule
import com.mosl.mobile.rm.core.MOSLCommonActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [CommonActivityBuilder::class])
abstract class DebugActivityBuilder {
  @ActivityScoped
  @ContributesAndroidInjector(
    modules = [CommonActivityModule::class, DebugFragmentsProvider::class, DebugActivityModule::class]
  )
  abstract fun bindCommonActivity(): MOSLCommonActivity
}