package com.mosl.mobile.volunteer.schedule

import com.mosl.mobile.volunteer.data.schedule.ScheduleRepositoryImpl
import com.mosl.mobile.volunteer.domain.schedule.ScheduleRepository
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