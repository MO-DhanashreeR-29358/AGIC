package com.mosl.mobile.volunteer

import com.mosl.annotations.ActivityScoped
import com.mosl.mobile.volunteer.core.CommonActivityModule
import com.mosl.mobile.volunteer.core.MOSLCommonActivity
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