package com.mosl.mobile.volunteer.appuser.login

import com.mosl.data.api.fcmtoken.FcmTokenRequest
import com.mosl.mobile.volunteer.appuser.model.LoginRequest
import com.mosl.mobile.volunteer.appuser.model.RmUser
import com.mosl.mobile.volunteer.appuser.model.Volunteer
import io.reactivex.Completable
import io.reactivex.Observable

interface LoginRepository {
    fun login(loginInfo: LoginRequest): Observable<Volunteer>
    fun logout(logoutInfo: FcmTokenRequest): Completable
}