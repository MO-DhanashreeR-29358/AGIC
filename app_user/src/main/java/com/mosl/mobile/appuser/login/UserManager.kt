package com.mosl.mobile.appuser.login

import com.mosl.mobile.appuser.model.RmUser
import com.mosl.mobile.appuser.model.User

/**
 * Created by lalkishore on 3/6/2017.
 */

interface UserManager {

    fun getPdf(): String
    fun getUser(): RmUser?
    fun isLoggedIn(): Boolean
    fun isActive(): Boolean
    fun doLogout()
}

val UserManager.user: RmUser
    get() {
        return getUser() ?: error("Not Logged In")
    }