package com.mosl.mobile.volunteer.overviewpager

import com.mosl.mobile.volunteer.data.overviewpager.OverviewPagerRepositoryImpl
import com.mosl.mobile.volunteer.domain.overviewpager.OverviewRepository
import dagger.Module
import dagger.Provides

@Module
class OverviewPagerModule {

    @Provides
    internal fun ProvideOverviewPagerFragment(fragment: OverviewFragment): OverviewPagerContract {
        return fragment
    }

    @Provides
    internal fun provideScheduleRepository(overviewPagerRepository: OverviewPagerRepositoryImpl): OverviewRepository {
        return overviewPagerRepository
    }
}