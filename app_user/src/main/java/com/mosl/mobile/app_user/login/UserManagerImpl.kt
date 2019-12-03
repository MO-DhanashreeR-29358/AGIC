package com.mosl.mobile.login

import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.model.User
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by lalkishore on 3/6/2017.
 */
@Singleton
class UserManagerImpl @Inject internal constructor(
    private val userCache: UserCache
) : UserManager {

    private var user: User?
    private var isActive: Boolean = false

    init {
        val user = userCache.getUser()
        val isActive = userCache.getNewsLetterState()
        this.user = user
        this.isActive = isActive
    }

    fun saveUser(user: User) {
        userCache.saveUser(user)
        this.user = user
    }

    fun saveNewsLetterState(isActive: Boolean) {
        userCache.saveNewsLetterState(isActive)
        this.isActive = isActive

    }

    override fun getUser(): User? {
        return user
    }

    override fun isLoggedIn(): Boolean {
        return user != null
    }

    override fun isActive(): Boolean {
        return isActive
    }

}
