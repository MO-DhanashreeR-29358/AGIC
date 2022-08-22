package com.mosl.volunteer.login

import com.mosl.volunteer.appuser.model.RmUser


/**
 * Created by lalkishore on 3/6/2017.
 */

internal interface UserCache {

    fun savePdf(pdfLink: String)
    fun saveUser(user: RmUser)
    fun getPdf(): String
    fun getUser(): RmUser?
    fun clearUser()
    fun saveNewsLetterState(isActive:Boolean)
    fun getNewsLetterState():Boolean
}
