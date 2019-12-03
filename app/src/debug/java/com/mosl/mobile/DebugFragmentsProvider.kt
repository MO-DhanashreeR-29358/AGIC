package com.mosl.mobile

import com.mosl.mobile.dashboard.DashboardFragment
import com.mosl.mobile.dashboard.DashboardModule
import com.mosl.mobile.overview.OverViewFragment
import com.mosl.mobile.drawermenu.DrawerMenuFragment
import com.mosl.mobile.featurescope.FirstPlaygroundFragment
import com.mosl.mobile.featurescope.SecondPlaygroundFragment
import com.mosl.mobile.panel.PanelFragment
import com.mosl.mobile.panel.PanelModule
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
    abstract fun provideOverViewFragmentFactory(): OverViewFragment

    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun provideScheduleFragmenttFactory(): ScheduleFragment

    @ContributesAndroidInjector(modules = [PanelModule::class])
    abstract fun providePanelFragmentFactory(): PanelFragment

    @ContributesAndroidInjector
    abstract fun provideDrawerMenuFragmentFactory(): DrawerMenuFragment
}