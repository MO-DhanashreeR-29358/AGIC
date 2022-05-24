package com.mosl.mobile.appuser.login

import com.mosl.mobile.appuser.model.RmUser
import com.mosl.mobile.appuser.model.User
import com.mosl.mobile.login.UserCache
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by lalkishore on 3/6/2017.
 */
@Singleton
class UserManagerImpl @Inject internal constructor(
    private val userCache: UserCache
) : UserManager {

    private var user: RmUser?
    private var isActive: Boolean = false
    private var pdfLink: String = ""

    init {
        val user = userCache.getUser()
        val isActive = userCache.getNewsLetterState()
        this.user = user
        this.isActive = isActive
        this.pdfLink = userCache.getPdf()
    }

    fun saveUser(user: RmUser) {
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

    override fun getPdf(): String {
        return pdfLink
    }

    override fun getUser(): RmUser? {
        return user
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

}
