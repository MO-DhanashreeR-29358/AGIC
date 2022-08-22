package com.mosl.volunteer.schedule

import com.mosl.volunteer.data.schedule.ScheduleRepositoryImpl
import com.mosl.volunteer.domain.schedule.ScheduleRepository
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