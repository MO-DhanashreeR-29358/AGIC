package com.mosl.mobile.portfolio

import dagger.Module
import dagger.Provides

@Module
class PortfolioModule {

  @Provides
  internal fun ProvidePortfolioFragment(fragment: PortfolioFragment):PortfolioView{
    return fragment
  }
}