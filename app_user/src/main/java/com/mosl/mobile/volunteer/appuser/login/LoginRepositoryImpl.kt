package com.mosl.mobile.volunteer.appuser.login

import com.mosl.data.api.AGICApi
import com.mosl.data.api.fcmtoken.FcmTokenRequest
import com.mosl.data.api.login.RmDataResponse
import com.mosl.data.api.login.VolunteerResponse
import com.mosl.mobile.volunteer.appuser.model.*
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
    override fun login(loginInfo: LoginRequest): Observable<Volunteer> {

        val loginInfoRequest = loginInfo.mapToLoginRequest()

        return agicApi.userLogin(loginInfoRequest).map {
            val user = it.data.mapToCorporateModel(it.message)
            userManager.saveUser(user)
            return@map user
        }
    }

    fun RmDataResponse.saveRmDataResponse(dataResponse: RmDataResponse,message: String): RmUser {
        //userManager.saveUser(this.mapToRmData(dataResponse,message))

        return this.mapToRmData(dataResponse,message)
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