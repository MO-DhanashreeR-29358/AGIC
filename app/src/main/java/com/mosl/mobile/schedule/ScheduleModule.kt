package com.mosl.mobile.schedule

import com.mosl.mobile.data.newsletter.NewsLetterRepositoryImpl
import com.mosl.mobile.data.schedule.ScheduleRepositoryImpl
import com.mosl.mobile.domain.newsletter.NewsLetterRepository
import com.mosl.mobile.domain.schedule.ScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class ScheduleModule {

    @Provides
    internal fun ProvideScheduleFragment(fragment: ScheduleFragment): ScheduleContract {
        return fragment
    }

//    @Provides
//    internal
//    fun provideScheduleRepository(scheduleRepository: ScheduleRepositoryImpl): ScheduleRepository {
//        return scheduleRepository
//    }

}