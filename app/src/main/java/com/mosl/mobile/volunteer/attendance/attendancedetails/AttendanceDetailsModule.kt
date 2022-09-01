package com.mosl.mobile.volunteer.attendance.attendancedetails

import dagger.Module
import dagger.Provides

@Module
class AttendanceDetailsModule {
    @Provides
    internal fun provideQrCodeScanFragment(fragment: AttendanceDetailsFragment): AttendanceDetailsContract {
        return fragment
    }
}