package com.mosl.mobile.volunteer.corporatepager

import com.mosl.mobile.volunteer.data.corporateschedulepager.CorporateSchedulePagerRepositoryImpl
import com.mosl.mobile.volunteer.domain.corporateschedulepager.CorporateScheduleRepository
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