package com.mosl.mobile.app_user.user

import android.content.Context
import android.content.SharedPreferences
import com.mosl.mobile.app_user.UserPreference
import com.mosl.mobile.app_user.helper.PrefStoreProvider
import com.mosl.mobile.app_user.helper.UserPrefStore
import com.mosl.mobile.app_user.helper.getPref
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.login.UserCache
import com.mosl.mobile.login.UserCacheImpl
import com.mosl.mobile.login.UserManagerImpl
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
    internal fun provideUserManager(userManagerImpl: UserManagerImpl): UserManager {
        return userManagerImpl
    }


    @Provides
    @Singleton
    internal fun provideUserCache(userCacheImpl: UserCacheImpl): UserCache {
        return userCacheImpl
    }


}