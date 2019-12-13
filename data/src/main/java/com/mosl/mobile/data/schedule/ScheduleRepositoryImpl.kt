package com.mosl.mobile.data.schedule

import com.google.gson.Gson
import com.mosl.app.core.prefs.StringPreference
import com.mosl.data.api.AGICApi
import com.mosl.data.api.schedule.ScheduleResponseItem
import com.mosl.mobile.annotations.LastScheduleDate
import com.mosl.mobile.annotations.ScheduleData
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
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
    @LastScheduleDate val scheduleDate: StringPreference,
    @ScheduleData val scheduleData: StringPreference,
    val gson: Gson
) : ScheduleRepository {

    private val mapper = ScheduleMapper

    val date = if (scheduleDate.isNotSet) {
        NEW
    } else
        getFormatedDate(scheduleDate.get(), DF_JSON_DATE_12, DF_TIME_HH_ss)

    val scheduleRequest = ScheduleRequestInfo(
        clientID = userManager.user.clientId,
        schedule = getTodaysDate(),
        timestamp = date
    )

    override fun getScheduleData(): Observable<ScheduleResponse> {

        return agicApi.getScheduleDataAPI(mapper.getScheduleRequestMap(scheduleRequest))

            .map {
                //                scheduleDate.set( DF_JSON_DATE_4.format(Date()))
                if (it.IsSucess) {
                    if (it.data == null) {
                        val json =
                            gson.toJson(ScheduleResponse(emptyList(), it.pdf, it.message))
                        scheduleData.set(json)
                    } else {

                        val json =
                            gson.toJson(ScheduleResponse(it.data?.dataMapper(), it.pdf, it.message))
                        scheduleData.set(json)
                    }


                }
                mapper.getScheduleMap(it.data, it.pdf, it.message)
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