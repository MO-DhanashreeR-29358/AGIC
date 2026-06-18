package com.mosl.mobile.rm

import com.mosl.mobile.rm.dashboard.DashboardFragment
import com.mosl.mobile.rm.dashboard.DashboardModule
import com.mosl.mobile.rm.drawermenu.DrawerMenuFragment
import com.mosl.mobile.rm.featurescope.FirstPlaygroundFragment
import com.mosl.mobile.rm.featurescope.SecondPlaygroundFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DebugFragmentsProvider {
    @ContributesAndroidInjector
    abstract fun provideFirstPlaygroundFragmentFactory(): FirstPlaygroundFragment

    @ContributesAndroidInjector
    abstract fun provideDeeplinkTestFragmentFactory(): SecondPlaygroundFragment

    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun provideDashboardFragmentFactory(): DashboardFragment


//    @ContributesAndroidInjector(modules = [DashboardModule::class])
//    abstract fun provideScheduleFragmenttFactory(): ScheduleFragment


    @ContributesAndroidInjector
    abstract fun provideDrawerMenuFragmentFactory(): DrawerMenuFragment
}