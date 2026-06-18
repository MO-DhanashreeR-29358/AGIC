package com.mosl.app.core.data.fcm

import android.content.Context
import com.google.gson.Gson
import com.mosl.annotations.FcmTokenRequest
import com.mosl.app.core.prefs.StringPreference
import com.mosl.app.core.utils.getUuid
import com.mosl.data.api.AGICApi
import com.mosl.mobile.appuser.login.UserManager
import com.mosl.mobile.appuser.login.user
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FcmTokenSyncHelper @Inject constructor(
    val context: Context,
    val userManager: UserManager,
    @FcmTokenRequest private val fcmTokenRequest: StringPreference,
    val gson: Gson,
    val agicApi: AGICApi
) {

    fun saveToken(token: String): Observable<String> {
        return Observable.fromCallable {
            val uuid = context.getUuid()
            com.mosl.data.api.fcmtoken.FcmTokenRequest(
                token,
                uuid,
                PLATFORM,
                userManager.user.empCode

            )
        }
            .flatMap { it ->
                gson.toJson(it)
                    ?.let { request ->
                        if (request == fcmTokenRequest.get()) {
                            Observable.just("NoNeed")
                        } else {

                            agicApi.updateFCMToken(it)
                                .filter { t -> t.isSuccess() }
                                .map { t -> t.message }
                                .doOnNext { fcmTokenRequest.set(request) }

                        }
                    }
            }
            .subscribeOn(Schedulers.io())
    }

    companion object {
        const val PLATFORM = "Android"
    }
}
