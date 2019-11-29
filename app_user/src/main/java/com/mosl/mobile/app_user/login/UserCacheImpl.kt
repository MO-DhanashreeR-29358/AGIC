package com.mosl.mobile.login

import com.google.gson.Gson
import com.mosl.mobile.app_user.helper.UserPrefStore
import com.mosl.mobile.app_user.model.User
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by lalkishore on 3/8/2017.
 */
@Singleton
class UserCacheImpl @Inject constructor(
    private val gson: Gson,
    private val userStore: UserPrefStore
) : UserCache {


    override fun saveUser(user: User) {
        userStore.userInfo.set(gson.toJson(user))
    }

    override fun getUser(): User? {
        return when {
            userStore.userInfo.isSet -> gson.fromJson(
                userStore.userInfo.get(),
                User::class.java
            )
            else -> null
        }
    }

    override fun clearUser() {
        userStore.clear()
    }
}
