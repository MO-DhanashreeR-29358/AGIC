package com.mosl.mobile

import com.mosl.mobile.portfolio.PortfolioFragmentProvider
import dagger.Module

@Module(includes = [PortfolioFragmentProvider::class])
abstract class FragmentProvider {
}