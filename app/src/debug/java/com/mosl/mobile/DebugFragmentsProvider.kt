package com.mosl.mobile

import com.mosl.mobile.dashboard.DashboardFragment
import com.mosl.mobile.dashboard.DashboardModule
import com.mosl.mobile.drawermenu.DrawerMenuFragment
import com.mosl.mobile.featurescope.FirstPlaygroundFragment
import com.mosl.mobile.featurescope.SecondPlaygroundFragment
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

  @ContributesAndroidInjector
  abstract fun provideDrawerMenuFragmentFactory(): DrawerMenuFragment
}