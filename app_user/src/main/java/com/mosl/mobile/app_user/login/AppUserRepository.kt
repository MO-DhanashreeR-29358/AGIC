package com.mosl.mobile.user.login

import com.mosl.data.api.fcm_token.FcmTokenRequest
import io.reactivex.Observable

/**
 * Created by lalkishore on 13/01/18.
 */

interface AppUserRepository {
  fun logout(logoutInfo: FcmTokenRequest): Observable<Boolean>
}
