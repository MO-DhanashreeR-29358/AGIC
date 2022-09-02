package com.mosl.mobile.volunteer.mock_data

import android.content.Context
import com.mosl.data.api.AGICApi
import com.mosl.data.api.BaseResponseAgic
import com.mosl.data.api.attendance.ClientDetails
import com.mosl.data.api.bytes.BytesInfoRequest
import com.mosl.data.api.bytes.BytesInfoResponse
import com.mosl.data.api.attendance.GetClientMeetingRequest
import com.mosl.data.api.attendance.SubmitClientAttendanceRequest
import com.mosl.data.api.corporateschedulepager.CorporateScheduleRequest
import com.mosl.data.api.corporateschedulepager.CorporateScheduleResponseDayItem
import com.mosl.data.api.demo.LoginInfo
import com.mosl.data.api.fcmtoken.FcmTokenRequest
import com.mosl.data.api.fcmtoken.FcmTokenResponse
import com.mosl.data.api.feedback.FeedbackRequest
import com.mosl.data.api.feedback.FeedbackResponseItem
import com.mosl.data.api.feedback.SubmitFeedbackRequest
import com.mosl.data.api.login.LoginInfoRequest
import com.mosl.data.api.login.VolunteerResponse
import com.mosl.data.api.newsletter.NewsLetterResponseItem
import com.mosl.data.api.notification.NotificationRequest
import com.mosl.data.api.notification.NotificationResponseItem
import com.mosl.data.api.overviewpager.*
import com.mosl.data.api.panel.PanelDataResponse
import com.mosl.data.api.schedule.ScheduleRequest
import com.mosl.data.api.schedule.ScheduleResponseItem
import com.mosl.mobile.volunteer.demo.UserResponse
import io.reactivex.Observable
import retrofit2.mock.MockRetrofit

class MockMoslApiServiceImpl(
    private val mockRetrofit: MockRetrofit,
    private val context: Context
) : AGICApi {
    override fun getCorporateScheduleAPI(request: CorporateScheduleRequest): Observable<BaseResponseAgic<List<CorporateScheduleResponseDayItem>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getScheduleDataAPI(request: ScheduleRequest): Observable<BaseResponseAgic<List<ScheduleResponseItem>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getClientIDOverAPI(request: String): Observable<OverviewResponseData> {
        TODO("Not yet implemented")
    }

    override fun getRMDayWiseDataAPI(request: RMDayWiseDataRequest): Observable<RmOverViewResponseData> {
        TODO("Not yet implemented")
    }

    override fun getRMClientWiseDataAPI(request: RMDayWiseDataRequest): Observable<RmClientWiseResponseData> {
        TODO("Not yet implemented")
    }

    override fun getByteDetails(request: BytesInfoRequest): Observable<List<BytesInfoResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getClientIDOverAPI(request: OverviewRequest): Observable<OverviewResponseData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBrochureData(): Observable<BaseResponseAgic<List<NewsLetterResponseItem>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPanelDetails(): Observable<PanelDataResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateFCMToken(fcmTokenRequest: FcmTokenRequest): Observable<FcmTokenResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun tokenDeregistration(fcmTokenRequest: FcmTokenRequest): Observable<FcmTokenResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun demoLogin(loginInfo: LoginInfo): UserResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun userLogin(request: LoginInfoRequest): Observable<BaseResponseAgic<List<VolunteerResponse>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getClientMeeting(request: GetClientMeetingRequest): Observable<BaseResponseAgic<ClientDetails>> {
        TODO("Not yet implemented")
    }

    override fun submitClientAttendance(request: SubmitClientAttendanceRequest): Observable<BaseResponseAgic<String>> {
        TODO("Not yet implemented")
    }

    override fun getNotification(notificationRequest: NotificationRequest): Observable<List<NotificationResponseItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFeedback(feedbackRequest: FeedbackRequest): Observable<List<FeedbackResponseItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun submitFeedback(feedbackRequest: SubmitFeedbackRequest): Observable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
