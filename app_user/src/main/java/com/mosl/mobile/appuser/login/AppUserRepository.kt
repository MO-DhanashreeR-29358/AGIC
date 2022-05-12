package com.mosl.mobile.appuser.login

import com.mosl.data.api.fcmtoken.FcmTokenRequest
import io.reactivex.Observable

/**
 * Created by lalkishore on 13/01/18.
 */

interface AppUserRepository {
  fun logout(logoutInfo: FcmTokenRequest): Observable<Boolean>
}
