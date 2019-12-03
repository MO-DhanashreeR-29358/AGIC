package com.mosl.mobile.app_user.helper

import android.content.SharedPreferences
import com.mosl.app.core.prefs.BooleanPreference
import com.mosl.app.core.prefs.PrefStore
import com.mosl.app.core.prefs.StringPreference


object PrefStoreProvider {
    const val PREF_USER_INFO = "user_info"
    const val PREF_NEWS_LETTER_STATE = "news_letter_state"
    const val PREF_CLIENT_CODE = "client_code"

    fun createUserPrefStore(
        preference: SharedPreferences
    ): UserPrefStore {
        return UserPrefStore(
            userInfo = StringPreference(preference, PREF_USER_INFO))
    }

    fun createNewsLetterStatePrefStore(
        preference: SharedPreferences
    ): NewsLetterPrefStateStore {
        return NewsLetterPrefStateStore(
            newsLetterState = BooleanPreference(preference, PREF_NEWS_LETTER_STATE)
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

data class NewsLetterPrefStateStore(
    @JvmField val newsLetterState: BooleanPreference
) : PrefStore {
    override fun clear() {
        newsLetterState.delete()
    }
}