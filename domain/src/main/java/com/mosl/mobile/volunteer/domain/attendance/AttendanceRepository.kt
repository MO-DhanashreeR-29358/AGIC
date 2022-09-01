package com.mosl.mobile.volunteer.domain.attendance

import io.reactivex.Observable

interface AttendanceRepository {
    fun getClientMeeting(
        uniqueId: String,
        companyId: String,
        confDate: String
    ): Observable<ClientMeetingDetailsModel>
}