package com.mosl.mobile.portfolio

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PortfolioFragmentProvider {
  @ContributesAndroidInjector(modules = [PortfolioModule::class])
  abstract fun providePortfolioFragmentFactory(): PortfolioFragment
}