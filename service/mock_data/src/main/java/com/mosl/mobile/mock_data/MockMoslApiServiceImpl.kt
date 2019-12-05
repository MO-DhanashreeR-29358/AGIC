package com.mosl.mobile.mock_data

import android.content.Context
import com.mosl.data.api.demo.LoginInfo
import com.mosl.data.api.AGICApi
import com.mosl.data.api.BaseResponseAgic
import com.mosl.data.api.login.LoginInfoRequest
import com.mosl.data.api.login.LoginInfoResponse
import com.mosl.data.api.newsletter.NewsLetterResponseItem
import com.mosl.data.api.schedule.ScheduleRequest
import com.mosl.data.api.schedule.ScheduleResponseItem
import com.mosl.data.api.panel.PanelDataResponse
import com.mosl.mobile.demo.UserResponse
import io.reactivex.Observable
import retrofit2.mock.MockRetrofit

class MockMoslApiServiceImpl(
    private val mockRetrofit: MockRetrofit,
    private val context: Context) : AGICApi {
    override fun getScheduleDataAPI(request: ScheduleRequest): Observable<List<ScheduleResponseItem>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBrochureData(): Observable<BaseResponseAgic<List<NewsLetterResponseItem>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPanelDetails(): Observable<PanelDataResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun demoLogin(loginInfo: LoginInfo):UserResponse{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun userLogin(request: LoginInfoRequest): Observable<LoginInfoResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
