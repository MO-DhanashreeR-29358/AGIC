package com.mosl.mobile.overviewpager

import com.mosl.mobile.data.overviewpager.OverviewPagerRepositoryImpl
import com.mosl.mobile.domain.overviewpager.OverviewPagerRepository
import dagger.Module
import dagger.Provides

@Module
class OverviewPagerModule {

    @Provides
    internal fun ProvideOverviewPagerFragment(fragment: OverviewFragment): OverviewPagerContract {
        return fragment
    }

    @Provides
    internal fun provideScheduleRepository(overviewPagerRepository: OverviewPagerRepositoryImpl): OverviewPagerRepository {
        return overviewPagerRepository
    }
}