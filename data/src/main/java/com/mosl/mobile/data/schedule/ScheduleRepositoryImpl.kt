package com.mosl.mobile.data.schedule

import com.mosl.data.api.AGICApi
import com.mosl.mobile.data.util.cache.InMemoryCache
import com.mosl.mobile.domain.schedule.ScheduleRepository
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponse
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import io.reactivex.Observable
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi
) : ScheduleRepository {

    private val mapper = ScheduleMapper
    private var pdfLink: String? = ""

    override fun getScheduleData(scheduleInafoa: ScheduleRequestInfo): Observable<ScheduleResponse> {

        return agicApi.getScheduleDataAPI(mapper.getScheduleRequestMap(scheduleInafoa))

            .map {
                setPdf(it.pdf)
                mapper.getScheduleMap(it.data, it.pdf)
            }
    }

    override fun getPdf(): String {
        return pdfLink ?: ""
    }

    override fun setPdf(pdfLink: String?) {
        this.pdfLink = pdfLink
    }

    companion object{
        const val PDF_LINK = "pdf_link"
    }

}