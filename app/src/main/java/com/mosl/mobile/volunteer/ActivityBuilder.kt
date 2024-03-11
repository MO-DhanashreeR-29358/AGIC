package com.mosl.mobile.volunteer

import com.mosl.annotations.ActivityScoped
import com.mosl.mobile.volunteer.core.CommonActivityModule
import com.mosl.mobile.volunteer.core.MOSLCommonActivity
import com.mosl.mobile.volunteer.core.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [CommonActivityBuilder::class])
abstract class ActivityBuilder {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [CommonActivityModule::class, MainActivityModule::class])
    internal abstract fun bindCommonActivity(): MOSLCommonActivity
}