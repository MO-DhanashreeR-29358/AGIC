package com.mosl.mobile.volunteer.attendance.corporateqr

import com.mosl.mobile.volunteer.data.attendance.AttendanceRepositoryImpl
import com.mosl.mobile.volunteer.domain.attendance.AttendanceRepository
import dagger.Module
import dagger.Provides

@Module
class QrCodeScanModule {

    @Provides
    fun provideQrCodeScanFragment(fragment: QrCodeScanFragment): QrCodeScanContract {
        return fragment
    }

    @Provides
    fun provideAttendanceRepository(attendanceRepository: AttendanceRepositoryImpl): AttendanceRepository {
        return attendanceRepository
    }
}