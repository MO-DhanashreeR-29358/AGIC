package com.mosl.mobile.app_user.login

import com.mosl.mobile.app_user.model.LoginRequest
import com.mosl.mobile.app_user.model.LoginResponse
import io.reactivex.Observable

interface LoginRepository {
    fun login(loginInfo: LoginRequest): Observable<LoginResponse>
}