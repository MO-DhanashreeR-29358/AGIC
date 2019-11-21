package com.mosl.mobile

import com.mosl.annotations.ActivityScoped
import com.mosl.mobile.core.CommonActivityModule
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.core.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [CommonActivityBuilder::class])
abstract class ActivityBuilder {
  @ActivityScoped
  @ContributesAndroidInjector(modules = [CommonActivityModule::class, MainActivityModule::class])
  internal abstract fun bindCommonActivity(): MOSLCommonActivity
}