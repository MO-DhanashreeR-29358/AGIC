package com.mosl.mobile.data.schedule

import com.mosl.app.core.prefs.StringPreference
import com.mosl.data.api.AGICApi
import com.mosl.mobile.annotations.LastScheduleDate
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.domain.schedule.ScheduleRepository
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponse
import com.mosl.shared.AppDateFormats.DF_JSON_DATE_4
import com.mosl.shared.AppDateFormats.DF_TIME_HH_ss
import com.mosl.shared.util.getFormatedDate
import com.mosl.shared.util.getTodaysDate
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi,
    val userManager: UserManager,
    @LastScheduleDate val scheduleDate: StringPreference
) : ScheduleRepository {

    private val mapper = ScheduleMapper

    val date = if (scheduleDate.isNotSet) {
        NEW
    } else
        getFormatedDate(scheduleDate.get(), DF_JSON_DATE_4, DF_TIME_HH_ss)

    val scheduleRequest = ScheduleRequestInfo(
        clientID = userManager.user.clientId,
        schedule = getTodaysDate(),
        timestamp = date
    )

    override fun getScheduleData(): Observable<ScheduleResponse> {

        return agicApi.getScheduleDataAPI(mapper.getScheduleRequestMap(scheduleRequest))

            .map {
//                scheduleDate.set( DF_JSON_DATE_4.format(Date()))
                mapper.getScheduleMap(it.data, it.pdf)
            }
    }

    companion object {
        const val NEW = "New"
    }

}