package com.mosl.mobile.volunteer.appuser.login

import com.mosl.mobile.volunteer.appuser.model.RmUser
import com.mosl.mobile.volunteer.appuser.model.Volunteer

/**
 * Created by lalkishore on 3/6/2017.
 */

interface UserManager {

    fun getPdf(): String
    fun getUser(): Volunteer?
    fun isLoggedIn(): Boolean
    fun isActive(): Boolean
    fun doLogout()
}

val UserManager.user: Volunteer
    get() {
        return getUser() ?: error("Not Logged In")
    }