package com.mosl.mobile.app_user.login

import com.mosl.data.api.AGICApi
import com.mosl.mobile.app_user.model.*
import io.reactivex.Observable
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    val agicApi: AGICApi
) : LoginRepository {
    override fun login(loginInfo: LoginRequest): Observable<LoginResponse> {

        val loginInfoRequest = loginInfo.mapToLoginRequest()

        return agicApi.userLogin(loginInfoRequest).map {

            it.mapToLoginResponse()
        }


    }
}