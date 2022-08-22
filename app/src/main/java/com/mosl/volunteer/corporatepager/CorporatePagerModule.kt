package com.mosl.volunteer.corporatepager

import com.mosl.volunteer.data.corporateschedulepager.CorporateSchedulePagerRepositoryImpl
import com.mosl.volunteer.domain.corporateschedulepager.CorporateScheduleRepository
import dagger.Module
import dagger.Provides

@Module
class CorporatePagerModule {

//    @Binds
//    abstract fun provideCorporatePagerModule(fragment: CorporatePagerFragment): CorporateSchedulePagerContract

    @Provides
    internal fun ProvideOverviewPagerFragment(fragment: CorporatePagerFragment): CorporateSchedulePagerContract {
        return fragment
    }

    @Provides
    internal fun provideScheduleRepository(scheduleRepository: CorporateSchedulePagerRepositoryImpl): CorporateScheduleRepository {
        return scheduleRepository
    }
}