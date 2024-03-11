package com.mosl.mobile.volunteer.demo

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DemoFragmentProvider {
  @ContributesAndroidInjector(modules = [DemoModule::class])
  abstract fun providePortfolioFragmentFactory(): DemoFragment
}