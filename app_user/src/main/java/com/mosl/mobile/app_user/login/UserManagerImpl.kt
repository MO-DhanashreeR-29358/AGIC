package com.mosl.mobile.login

import com.mosl.mobile.app_user.login.UserManager
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by lalkishore on 3/6/2017.
 */
@Singleton
class UserManagerImpl @Inject internal constructor(
    private val userCache: UserCache
) : UserManager {
}