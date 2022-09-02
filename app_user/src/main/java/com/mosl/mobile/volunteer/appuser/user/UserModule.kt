package com.mosl.mobile.volunteer.appuser.user

import android.content.Context
import android.content.SharedPreferences
import com.mosl.mobile.volunteer.appuser.UserPreference
import com.mosl.mobile.volunteer.appuser.helper.*
import com.mosl.mobile.volunteer.appuser.login.UserManager
import com.mosl.mobile.volunteer.appuser.login.UserCache
import com.mosl.mobile.volunteer.appuser.login.UserCacheImpl
import com.mosl.mobile.volunteer.appuser.login.UserManagerImpl
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
    @Singleton
    fun provideClientPrefStore(
        @UserPreference preferences: SharedPreferences
    ): ClientPrefStore {
        return PrefStoreProvider.createClientPrefStore(preferences)
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