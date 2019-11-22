package com.mosl.mobile.demo

import dagger.Module
import dagger.Provides

@Module
class DemoMVVMModule {

  @Provides
  internal fun ProvidePortfolioFragment(fragment: DemoMVVMFragment):DemoMVVMView{
    return fragment
  }
}