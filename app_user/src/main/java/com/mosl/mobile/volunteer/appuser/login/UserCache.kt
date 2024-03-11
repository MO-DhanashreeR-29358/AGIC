package com.mosl.mobile.volunteer.appuser.login

import com.mosl.data.api.attendance.ClientDetails
import com.mosl.mobile.volunteer.appuser.model.Volunteer


/**
 * Created by lalkishore on 3/6/2017.
 */

internal interface UserCache {

    fun savePdf(pdfLink: String)
    fun saveUser(user: Volunteer)
    fun getPdf(): String
    fun getUser(): Volunteer?
    fun clearUser()
    fun saveNewsLetterState(isActive:Boolean)
    fun getNewsLetterState():Boolean
    fun getClientDetailsModel(): ClientDetails?
    fun saveClientDetailsModel(model: ClientDetails)
    fun clearClientDetailsModel()
}
