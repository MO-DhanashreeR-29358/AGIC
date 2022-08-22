package com.mosl.volunteer.rmclientwisepager

import com.mosl.volunteer.data.overviewpager.OverviewPagerRepositoryImpl
import com.mosl.volunteer.domain.overviewpager.OverviewRepository
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