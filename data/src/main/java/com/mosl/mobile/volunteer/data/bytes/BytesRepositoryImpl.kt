package com.mosl.mobile.volunteer.data.bytes

import com.mosl.app.core.prefs.StringPreference
import com.mosl.data.api.AGICApi
import com.mosl.mobile.volunteer.annotations.LastBytesDate
import com.mosl.mobile.volunteer.annotations.LastScheduleDate
import com.mosl.mobile.volunteer.appuser.login.UserManager
import com.mosl.mobile.volunteer.appuser.login.user
import com.mosl.mobile.volunteer.domain.bytes.BytesRepository
import com.mosl.mobile.volunteer.domain.bytes.BytesRequest
import com.mosl.mobile.volunteer.domain.bytes.BytesResponse
import com.mosl.shared.AppDateFormats.DF_JSON_DATE_12
import com.mosl.shared.AppDateFormats.DF_JSON_DATE_4
import com.mosl.shared.util.getMilliFromDate
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class BytesRepositoryImpl @Inject constructor(
    var agicApi: AGICApi,
    val userManager: UserManager,
    @LastScheduleDate val lastScheduleDate: StringPreference,
    @LastBytesDate val lastBytesDate: StringPreference
) : BytesRepository {



    override fun bytesData(): Observable<List<BytesResponse>> {

        val bytesDate = if (lastBytesDate.isNotSet) {
            NEW
        } else
            getMilliFromDate(lastBytesDate.get(), DF_JSON_DATE_12).toString()

        val scheduleDate = if (lastScheduleDate.isNotSet) {
            getMilliFromDate(DF_JSON_DATE_4.format(Date()), DF_JSON_DATE_4).toString()
        } else
            getMilliFromDate(lastScheduleDate.get(), DF_JSON_DATE_12).toString()

        val bytesRequest = BytesRequest(
            bytesDate,
            scheduleDate,
            userManager.user.empCode
        )
        val requestMapper = bytesRequest.mapToBytesRequest()


        return agicApi.getByteDetails(requestMapper).map {
            //            lastBytesDate.set( DF_JSON_DATE_4.format(Date()))
            it.mapToBytesResponse()
        }
    }

    companion object {
        const val NEW = "New"
    }
}