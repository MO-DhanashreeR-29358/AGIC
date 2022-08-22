package com.mosl.volunteer

import com.mosl.annotations.ActivityScoped
import com.mosl.volunteer.core.CommonActivityModule
import com.mosl.volunteer.core.MOSLCommonActivity
import com.mosl.volunteer.core.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [CommonActivityBuilder::class])
abstract class ActivityBuilder {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [CommonActivityModule::class, MainActivityModule::class])
    internal abstract fun bindCommonActivity(): MOSLCommonActivity
}