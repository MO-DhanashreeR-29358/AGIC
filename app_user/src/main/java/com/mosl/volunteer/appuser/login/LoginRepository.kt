package com.mosl.volunteer.appuser.login

import com.mosl.data.api.fcmtoken.FcmTokenRequest
import com.mosl.volunteer.appuser.model.LoginRequest
import com.mosl.volunteer.appuser.model.RmUser
import io.reactivex.Completable
import io.reactivex.Observable

interface LoginRepository {
    fun login(loginInfo: LoginRequest): Observable<RmUser>
    fun logout(logoutInfo: FcmTokenRequest): Completable
}