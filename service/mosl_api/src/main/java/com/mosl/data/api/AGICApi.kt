package com.mosl.data.api

import com.mosl.data.api.demo.LoginInfo
import com.mosl.data.api.login.LoginInfoRequest
import com.mosl.data.api.login.LoginInfoResponse
import com.mosl.data.api.newsletter.NewsLetterResponseItem
import com.mosl.data.api.schedule.ScheduleRequest
import com.mosl.data.api.schedule.ScheduleResponseItem
import com.mosl.data.api.panel.PanelDataResponse
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
    fun getScheduleDataAPI(@Body request: ScheduleRequest): Observable<BaseResponseAgic<List<ScheduleResponseItem>>>


    @POST("GetPanelDetails")
    @Headers(CACHE_FOR_4HRS)
    fun getPanelDetails(): Observable<PanelDataResponse>


    companion object {
        const val CACHE_FOR_4HRS = "Cache-For: 240"
    }

}