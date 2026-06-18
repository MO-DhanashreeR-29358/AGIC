package com.mosl.mobile.rm

import com.mosl.annotations.ActivityScoped
import com.mosl.mobile.rm.core.CommonActivityModule
import com.mosl.mobile.rm.core.MOSLCommonActivity
import com.mosl.mobile.rm.core.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [CommonActivityBuilder::class])
abstract class ActivityBuilder {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [CommonActivityModule::class, MainActivityModule::class])
    internal abstract fun bindCommonActivity(): MOSLCommonActivity
}