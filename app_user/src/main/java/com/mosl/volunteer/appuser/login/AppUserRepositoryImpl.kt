package com.mosl.volunteer.appuser.login

import com.mosl.data.api.AGICApi
import com.mosl.data.api.fcmtoken.FcmTokenRequest
import io.reactivex.Observable
import javax.inject.Inject

class AppUserRepositoryImpl @Inject constructor(
    val agicApi: AGICApi
) : AppUserRepository {


    override fun logout(request: FcmTokenRequest): Observable<Boolean> {

        return agicApi.tokenDeregistration(request)
            .map { true }
    }
}