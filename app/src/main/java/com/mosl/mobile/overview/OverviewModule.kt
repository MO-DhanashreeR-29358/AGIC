package com.mosl.mobile.overview

import dagger.Module
import dagger.Provides

@Module
class OverviewModule {

    @Provides
    internal fun ProvideOverviewFragment(fragment: OverviewFragment): OverviewContract {
        return fragment
    }
}