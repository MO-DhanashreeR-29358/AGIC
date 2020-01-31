package com.mosl.mobile.app_user.login

import com.mosl.data.api.AGICApi
import com.mosl.data.api.fcm_token.FcmTokenRequest
import com.mosl.data.api.login.LoginInfoResponse
import com.mosl.mobile.app_user.model.LoginRequest
import com.mosl.mobile.app_user.model.User
import com.mosl.mobile.app_user.model.mapToLoginRequest
import com.mosl.mobile.app_user.model.mapToLoginResponse
import com.mosl.mobile.login.UserManagerImpl
import com.mosl.mobile.user.login.AppUserRepository
import com.mosl.mobile.user.login.AppUserRepositoryImpl
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    internal val agicApi: AGICApi,
    internal val userManager: UserManagerImpl,
    internal val userRepository: AppUserRepositoryImpl

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

    override fun logout(logoutInfo: FcmTokenRequest): Completable {
        return Completable.fromAction {
            if (userManager.isLoggedIn()) {
                val loggedOut = userRepository.logout(logoutInfo)
                    .onErrorReturnItem(false)
                    .blockingFirst()
                Timber.d("Logout Success : %s", loggedOut)
                userManager.doLogout()
            }
        }
            .subscribeOn(Schedulers.io())
    }

}