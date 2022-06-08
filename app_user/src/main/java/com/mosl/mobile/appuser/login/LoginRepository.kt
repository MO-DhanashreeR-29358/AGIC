package com.mosl.mobile.appuser.login

import com.mosl.data.api.fcmtoken.FcmTokenRequest
import com.mosl.mobile.appuser.model.LoginRequest
import com.mosl.mobile.appuser.model.RmUser
import com.mosl.mobile.appuser.model.User
import io.reactivex.Completable
import io.reactivex.Observable

interface LoginRepository {
    fun login(loginInfo: LoginRequest): Observable<RmUser>
    fun logout(logoutInfo: FcmTokenRequest): Completable
}