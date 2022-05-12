package com.mosl.mobile.data.schedule

import com.mosl.app.core.prefs.StringPreference
import com.mosl.data.api.AGICApi
import com.mosl.data.api.schedule.ScheduleResponseItem
import com.mosl.mobile.annotations.LastScheduleDate
import com.mosl.mobile.appuser.login.UserManager
import com.mosl.mobile.appuser.login.user
import com.mosl.mobile.domain.schedule.*
import com.mosl.shared.AppDateFormats.DF_JSON_DATE_12
import com.mosl.shared.AppDateFormats.DF_TIME_HH_ss
import com.mosl.shared.util.getFormatedDate
import com.mosl.shared.util.getTodaysDate
import io.reactivex.Observable
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi,
    val userManager: UserManager,
    @LastScheduleDate val scheduleDate: StringPreference
) : ScheduleRepository {

    private val mapper = ScheduleMapper



    override fun getScheduleData(): Observable<ScheduleResponse> {

        val date = if (scheduleDate.get().isEmpty()) {
            NEW
        } else
            getFormatedDate(scheduleDate.get(), DF_JSON_DATE_12, DF_TIME_HH_ss)

        val scheduleRequest = ScheduleRequestInfo(
            clientID = userManager.user.clientId,
            schedule = getTodaysDate(),
            timestamp = date
        )

        return agicApi.getScheduleDataAPI(mapper.getScheduleRequestMap(scheduleRequest))

            .map {

                mapper.getScheduleMap(it.data, it.pdf, it.message,it.feedbackPending,it.notificationPending)
            }
    }

    private fun List<ScheduleResponseItem>.dataMapper(): List<ScheduleResponseItemInfo> {
        if (isNullOrEmpty()) return emptyList()

        return map {
            ScheduleResponseItemInfo(
                Rep = it.Rep.map {
                    ScheduleRepresentativeItemData(
                        name = it.name,
                        designation = it.designation
                    )
                },

                recordNo = it.recordNo,
                ReportURL = it.ReportURL,
                companyName = it.companyName,
                roomNo = it.roomNo,
                scheduleDate = it.ScheduleDate,
                fundId = it.fundId,
                startTime = it.startTime,
                endTime = it.endTime,
                natureOfMeeting = it.NatureOfMeeting,
                updateFlag = it.updateFlag,
                updateDate = it.UpdateDate
            )
        }
    }

    companion object {
        const val NEW = "New"
    }

}