package com.mosl.volunteer

import com.mosl.volunteer.dashboard.DashboardFragment
import com.mosl.volunteer.dashboard.DashboardModule
import com.mosl.volunteer.drawermenu.DrawerMenuFragment
import com.mosl.volunteer.featurescope.FirstPlaygroundFragment
import com.mosl.volunteer.featurescope.SecondPlaygroundFragment
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