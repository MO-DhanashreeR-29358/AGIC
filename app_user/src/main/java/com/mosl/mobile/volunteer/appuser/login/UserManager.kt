package com.mosl.mobile.volunteer.appuser.login

import com.mosl.mobile.volunteer.appuser.model.RmUser

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