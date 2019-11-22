package com.mosl.mobile.demo

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DemoMVVMFragmentProvider {
  @ContributesAndroidInjector(modules = [DemoMVVMModule::class])
  abstract fun providePortfolioFragmentFactory(): DemoMVVMFragment
}