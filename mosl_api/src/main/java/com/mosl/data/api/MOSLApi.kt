package com.mosl.data.api

import com.mosl.mobile.demo.AuthResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MOSLApi {

  @FormUrlEncoded
  @POST("login")
  suspend fun userLogin(
    @Field("email") email: String,
    @Field("password") password: String
  ): AuthResponse

}