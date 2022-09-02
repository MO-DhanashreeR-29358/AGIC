package com.mosl.mobile.volunteer.data.attendance

import com.mosl.data.api.AGICApi
import com.mosl.data.api.attendance.GetClientMeetingRequest
import com.mosl.mobile.volunteer.data.attendance.AttendanceMapper.mapToClientDataModel
import com.mosl.mobile.volunteer.domain.attendance.AttendanceRepository
import com.mosl.mobile.volunteer.domain.attendance.ClientDetailsModel
import io.reactivex.Observable
import javax.inject.Inject

class AttendanceRepositoryImpl
    @Inject constructor(
    private val api: AGICApi
): AttendanceRepository {
    override fun getClientMeeting(
        uniqueId: String,
        companyId: String,
        confDate: String,
    ): Observable<ClientDetailsModel> {
        val request = GetClientMeetingRequest(uniqueId, companyId, confDate)
        return api.getClientMeeting(request)
            .map {
                it.data.mapToClientDataModel()
            }
    }
}