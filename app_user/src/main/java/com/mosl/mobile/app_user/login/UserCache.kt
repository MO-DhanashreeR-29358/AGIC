package com.mosl.mobile.login

import com.mosl.mobile.app_user.model.User


/**
 * Created by lalkishore on 3/6/2017.
 */

internal interface UserCache {

    fun saveUser(user: User)
    fun getUser(): User?
    fun clearUser()
}
