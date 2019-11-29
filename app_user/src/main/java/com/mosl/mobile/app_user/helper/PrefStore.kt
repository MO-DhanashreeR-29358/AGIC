package com.mosl.mobile.app_user.helper

import android.content.SharedPreferences
import com.mosl.app.core.prefs.PrefStore
import com.mosl.app.core.prefs.StringPreference


object PrefStoreProvider {
    const val PREF_USER_INFO = "user_info"
    const val PREF_CLIENT_CODE = "client_code"

    fun createUserPrefStore(
        preference: SharedPreferences
    ): UserPrefStore {
        return UserPrefStore(
            userInfo = StringPreference(preference, PREF_USER_INFO)
        )
    }

}

data class UserPrefStore(
    @JvmField val userInfo: StringPreference
) : PrefStore {
    override fun clear() {
        userInfo.delete()
    }
}