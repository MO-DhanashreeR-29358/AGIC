package com.mosl.login.api

import com.mosl.annotations.ExcludeWrapper
import com.mosl.api.core.BaseResponse
import com.mosl.login.api.model.LoginEndpoints.Authorize
import com.mosl.login.api.model.LoginEndpoints.Logout
import com.mosl.login.api.model.LoginEndpoints.Token
import com.mosl.login.api.model.NO_AUTH_HEADER
import com.mosl.login.api.model.request.ChangePasswordRequest
import com.mosl.login.api.model.request.ForgotPasswordRequest
import com.mosl.login.api.model.request.LoginRequest
import com.mosl.login.api.model.request.SignUpRequest
import com.mosl.login.api.model.request.TokenRequest
import com.mosl.login.api.model.response.AuthProfile
import com.mosl.login.api.model.response.TokenResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by lalkishore on 13/01/18.
 */

interface LoginApi {
  @POST("Login/$Authorize")
  @Headers(NO_AUTH_HEADER)
  fun loginUser(@Body login: LoginRequest): Observable<AuthProfile>

  @POST("Login/$Authorize")
  fun validateUser(
      @Body login: LoginRequest
  ): Observable<AuthProfile>

  @POST("Login/$Token")
  @Headers(NO_AUTH_HEADER)
  fun refreshToken(@Body login: TokenRequest): Observable<TokenResponse>

  @POST("Login/ForgotPasswordV2")
  @ExcludeWrapper
  @Headers(NO_AUTH_HEADER)
  fun forgotPassword(@Body body: ForgotPasswordRequest): Observable<BaseResponse<String>>

  @POST("User/ChangePassword")
  fun changePassword(
      @Body body: ChangePasswordRequest
  ): Observable<BaseResponse<String>>

  @POST("Login/GuestSignUp")
  @ExcludeWrapper
  @Headers(NO_AUTH_HEADER)
  fun guestSignUp(@Body request: SignUpRequest): Observable<BaseResponse<String>>

  @POST("Login/$Logout")
  fun logoutUser(@Body login: TokenRequest): Observable<String>
}
