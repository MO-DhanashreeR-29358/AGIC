package com.mosl.data.api

import com.mosl.data.api.bytes.BytesInfoRequest
import com.mosl.data.api.bytes.BytesInfoResponse
import com.mosl.data.api.corporateschedulepager.CorporateScheduleRequest
import com.mosl.data.api.corporateschedulepager.CorporateScheduleResponseDayItem
import com.mosl.data.api.demo.LoginInfo
import com.mosl.data.api.fcmtoken.FcmTokenRequest
import com.mosl.data.api.fcmtoken.FcmTokenResponse
import com.mosl.data.api.feedback.FeedbackRequest
import com.mosl.data.api.feedback.FeedbackResponseItem
import com.mosl.data.api.feedback.SubmitFeedbackRequest
import com.mosl.data.api.login.LoginInfoRequest
import com.mosl.data.api.login.LoginInfoResponse
import com.mosl.data.api.newsletter.NewsLetterResponseItem
import com.mosl.data.api.notification.NotificationRequest
import com.mosl.data.api.notification.NotificationResponseItem
import com.mosl.data.api.overviewpager.OverviewRequest
import com.mosl.data.api.overviewpager.OverviewResponseData
import com.mosl.data.api.panel.PanelDataResponse
import com.mosl.data.api.schedule.ScheduleRequest
import com.mosl.data.api.schedule.ScheduleResponseItem
import com.mosl.mobile.demo.UserResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AGICApi {

    @POST("QMS2API/api/Login/authorize")
    suspend fun demoLogin(
        @Body request: LoginInfo
    ): UserResponse

    @POST("GetClientDetails")
    fun userLogin(@Body request: LoginInfoRequest): Observable<LoginInfoResponse>

    @POST("GetBroucherDetails")
    fun getBrochureData(): Observable<BaseResponseAgic<List<NewsLetterResponseItem>>>

    @POST("GetScheduleAPI")
    @Headers(CACHE_FOR_4HRS)
    fun getScheduleDataAPI(@Body request: ScheduleRequest): Observable<BaseResponseAgic<List<ScheduleResponseItem>>>

    @POST("GetClientIDOver")
    @Headers(CACHE_FOR_4HRS)
    fun getClientIDOverAPI(@Body request: OverviewRequest): Observable<OverviewResponseData>

    @POST("GetbyteDetails")
    @Headers(CACHE_FOR_4HRS)
    fun getByteDetails(@Body request: BytesInfoRequest): Observable<List<BytesInfoResponse>>

    @POST("GetPanelDetails")
    @Headers(CACHE_FOR_4HRS)
    fun getPanelDetails(): Observable<PanelDataResponse>


    @POST("GetTokenForClient")
    fun updateFCMToken(@Body fcmTokenRequest: FcmTokenRequest): Observable<FcmTokenResponse>

    @POST("TokenDeregistration")
    fun tokenDeregistration(@Body fcmTokenRequest: FcmTokenRequest): Observable<FcmTokenResponse>

    @POST("NotificationHistory")
    @Headers(CACHE_FOR_4HRS)
    fun getNotification(@Body notificationRequest: NotificationRequest): Observable<List<NotificationResponseItem>>

    @POST("GetFeedbackQuestions")
    fun getFeedback(@Body feedbackRequest: FeedbackRequest): Observable<List<FeedbackResponseItem>>

    @POST("SubmitFeedback")
    fun submitFeedback(@Body feedbackRequest: SubmitFeedbackRequest): Observable<String>

    @POST("GetCorporateSchedule")
    @Headers(CACHE_FOR_4HRS)
    fun getCorporateScheduleAPI(@Body request: CorporateScheduleRequest): Observable<BaseResponseAgic<List<CorporateScheduleResponseDayItem>>>

    companion object {
        const val CACHE_FOR_4HRS = "Cache-For: 240"
    }

}