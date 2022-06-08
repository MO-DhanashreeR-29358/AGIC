package com.mosl.mobile.appuser.user

import android.content.Context
import android.content.SharedPreferences
import com.mosl.mobile.appuser.UserPreference
import com.mosl.mobile.appuser.helper.NewsLetterPrefStateStore
import com.mosl.mobile.appuser.helper.PrefStoreProvider
import com.mosl.mobile.appuser.helper.UserPrefStore
import com.mosl.mobile.appuser.helper.getPref
import com.mosl.mobile.appuser.login.UserManager
import com.mosl.mobile.login.UserCache
import com.mosl.mobile.login.UserCacheImpl
import com.mosl.mobile.appuser.login.UserManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserModule {

    private val USER_MANAGER_PREF = ".user_manager"


    @Provides
    @UserPreference
    @Singleton
    fun provideUserCredPreference(context: Context): SharedPreferences {
        return context.getPref(
            context.packageName + USER_MANAGER_PREF
        )
    }

    @Provides
    @Singleton
    fun provideUserPrefStore(
        @UserPreference preferences: SharedPreferences
    ): UserPrefStore {
        return PrefStoreProvider.createUserPrefStore(preferences)
    }

    @Provides
    @Singleton
    fun provideNewsLetterPref(
        @UserPreference preferences: SharedPreferences
    ): NewsLetterPrefStateStore {
        return PrefStoreProvider.createNewsLetterStatePrefStore(preferences)
    }

    @Provides
    internal fun provideUserManager(userManagerImpl: UserManagerImpl): UserManager {
        return userManagerImpl
    }


    @Provides
    @Singleton
    internal fun provideUserCache(userCacheImpl: UserCacheImpl): UserCache {
        return userCacheImpl
    }


}