package com.mosl.mobile.app_user.login

import com.mosl.data.api.AGICApi
import com.mosl.data.api.login.LoginInfoResponse
import com.mosl.mobile.app_user.model.LoginRequest
import com.mosl.mobile.app_user.model.User
import com.mosl.mobile.app_user.model.mapToLoginRequest
import com.mosl.mobile.app_user.model.mapToLoginResponse
import com.mosl.mobile.login.UserManagerImpl
import io.reactivex.Observable
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi,
    internal val userManager: UserManagerImpl
) : LoginRepository {
    override fun login(loginInfo: LoginRequest): Observable<User> {

        val loginInfoRequest = loginInfo.mapToLoginRequest()

        return agicApi.userLogin(loginInfoRequest).map {

            it.saveLoginResponse()
        }
    }

    fun LoginInfoResponse.saveLoginResponse(): User {
        userManager.saveUser(this.mapToLoginResponse())

        return this.mapToLoginResponse()
    }
}