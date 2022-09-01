package com.mosl.mobile.volunteer.appuser.login

import com.mosl.data.api.attendance.ClientMeetingDetailsModel
import com.mosl.mobile.volunteer.appuser.model.RmUser
import com.mosl.mobile.volunteer.appuser.model.Volunteer
import com.mosl.mobile.volunteer.login.UserCache
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by lalkishore on 3/6/2017.
 */
@Singleton
class UserManagerImpl @Inject internal constructor(
    private val userCache: UserCache
) : UserManager {

    private var user: Volunteer?
    private var isActive: Boolean = false
    private var pdfLink: String = ""

    init {
        val user = userCache.getUser()
        val isActive = userCache.getNewsLetterState()
        this.user = user
        this.isActive = isActive
        this.pdfLink = userCache.getPdf()
    }

    fun saveUser(user: Volunteer) {
        userCache.saveUser(user)
        this.user = user

    }

    fun savePdf(pdfLink: String) {
        userCache.savePdf(pdfLink)
        this.pdfLink = pdfLink
    }

    fun saveNewsLetterState(isActive: Boolean) {
        userCache.saveNewsLetterState(isActive)
        this.isActive = isActive

    }

    fun saveClientModel(model: ClientMeetingDetailsModel) {
        userCache.saveClientDetailsModel(model)
    }

    override fun getPdf(): String {
        return pdfLink
    }

    override fun getUser(): Volunteer? {
        return user
    }

    fun getClientModel(): ClientMeetingDetailsModel? {
        return userCache.getClientDetailsModel()
    }

    override fun isLoggedIn(): Boolean {
        return user != null
    }

    override fun isActive(): Boolean {
        return isActive
    }

    override fun doLogout() {
        user=null
        userCache.clearUser()
    }

    fun clearClientModel() {
        userCache.clearClientDetailsModel()
    }
}
