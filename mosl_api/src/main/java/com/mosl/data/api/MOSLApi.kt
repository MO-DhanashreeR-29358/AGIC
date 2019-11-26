package com.mosl.data.api

import com.mosl.data.api.demo.LoginInfo
import com.mosl.mobile.demo.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MOSLApi {

  @POST("QMS2API/api/Login/authorize")
  suspend fun userLogin(
    @Body request: LoginInfo
  ): UserResponse

}