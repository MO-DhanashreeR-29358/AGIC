package com.mosl.mobile.rm.schedule

import com.mosl.mobile.data.schedule.ScheduleRepositoryImpl
import com.mosl.mobile.domain.schedule.ScheduleRepository
import dagger.Module
import dagger.Provides

@Module
class ScheduleModule {

    @Provides
    internal fun ProvideScheduleFragment(fragment: ScheduleFragment): ScheduleContract {
        return fragment
    }

    @Provides
    internal
    fun provideScheduleRepository(scheduleRepository: ScheduleRepositoryImpl): ScheduleRepository {
        return scheduleRepository
    }

}