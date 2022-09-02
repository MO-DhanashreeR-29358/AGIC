package com.mosl.mobile.volunteer.attendance.attendancedetails

import com.mosl.mobile.volunteer.data.attendance.AttendanceRepositoryImpl
import com.mosl.mobile.volunteer.domain.attendance.AttendanceRepository
import dagger.Module
import dagger.Provides

@Module
class AttendanceDetailsModule {
    @Provides
    internal fun provideAttendanceDetailsFragment(fragment: AttendanceDetailsFragment): AttendanceDetailsContract {
        return fragment
    }

    @Provides
    fun provideAttendanceDetailsRepository(attendanceRepository: AttendanceRepositoryImpl): AttendanceRepository {
        return attendanceRepository
    }
}