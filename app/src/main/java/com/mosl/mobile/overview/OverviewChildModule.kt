package com.mosl.mobile.overview

import dagger.Module
import dagger.Provides

@Module
class OverviewChildModule {

    @Provides
    internal fun ProvideOverviewFragment(fragment: OverviewChildFragment): OverviewChildContract {
        return fragment
    }
}