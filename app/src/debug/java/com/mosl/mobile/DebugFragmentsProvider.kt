package com.mosl.mobile

import com.mosl.mobile.dashboard.DashboardFragment
import com.mosl.mobile.dashboard.DashboardModule
import com.mosl.mobile.overviewpager.OverviewFragment
import com.mosl.mobile.drawermenu.DrawerMenuFragment
import com.mosl.mobile.featurescope.FirstPlaygroundFragment
import com.mosl.mobile.featurescope.SecondPlaygroundFragment
import com.mosl.mobile.panel.PanelChildFragment
import com.mosl.mobile.panel.PanelChildModule
import com.mosl.mobile.schedule.ScheduleFragment
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

    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun provideOverViewFragmentFactory(): OverviewFragment

    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun provideScheduleFragmenttFactory(): ScheduleFragment

    @ContributesAndroidInjector(modules = [PanelChildModule::class])
    abstract fun providePanelFragmentFactory(): PanelChildFragment



    @ContributesAndroidInjector
    abstract fun provideDrawerMenuFragmentFactory(): DrawerMenuFragment
}