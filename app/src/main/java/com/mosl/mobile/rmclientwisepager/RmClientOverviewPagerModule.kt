package com.mosl.mobile.rmclientwisepager

import com.mosl.mobile.data.overviewpager.OverviewPagerRepositoryImpl
import com.mosl.mobile.domain.overviewpager.OverviewRepository
import dagger.Module
import dagger.Provides

@Module
class RmClientOverviewPagerModule {

    @Provides
    internal fun ProvideRmClientOverviewPagerFragment(fragment: RmClientOverviewFragment): RmClientOverviewPagerContract {
        return fragment
    }

    @Provides
    internal fun provideScheduleRepository(overviewPagerRepository: OverviewPagerRepositoryImpl): OverviewRepository {
        return overviewPagerRepository
    }
}