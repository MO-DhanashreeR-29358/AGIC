package com.mosl.mobile.rm.overview

import dagger.Module
import dagger.Provides

@Module
class OverviewChildModule {

    @Provides
    internal fun ProvideOverviewFragment(fragment: OverviewChildFragment): OverviewChildContract {
        return fragment
    }
}