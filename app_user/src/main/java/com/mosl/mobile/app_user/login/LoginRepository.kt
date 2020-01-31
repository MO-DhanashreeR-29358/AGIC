package com.mosl.mobile.app_user.login

import com.mosl.data.api.fcm_token.FcmTokenRequest
import com.mosl.mobile.app_user.model.LoginRequest
import com.mosl.mobile.app_user.model.User
import io.reactivex.Completable
import io.reactivex.Observable

interface LoginRepository {
    fun login(loginInfo: LoginRequest): Observable<User>
    fun logout(logoutInfo: FcmTokenRequest): Completable
}