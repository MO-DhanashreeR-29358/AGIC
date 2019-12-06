package com.mosl.mobile.app_user.login

import com.mosl.mobile.app_user.model.User

/**
 * Created by lalkishore on 3/6/2017.
 */

interface UserManager {

    fun getPdf(): String
    fun getUser(): User?
    fun isLoggedIn(): Boolean
    fun isActive(): Boolean
}

val UserManager.user: User
    get() {
        return getUser() ?: error("Not Logged In")
    }