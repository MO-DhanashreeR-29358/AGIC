package com.mosl.mobile.volunteer.domain.attendance

import io.reactivex.Observable

interface AttendanceRepository {
    fun getClientMeeting(
        uniqueId: String,
        companyId: String,
        confDate: String
    ): Observable<ClientDetailsModel>

    fun submitClientAttendance(
        userId: String,
        clientId: Int,
        fundId: Int,
        companyId: String,
        status: String,
        meetingId: Int,
        startTime: String,
        endTime: String,
        isAllowed: Int
    ): Observable<String>
}