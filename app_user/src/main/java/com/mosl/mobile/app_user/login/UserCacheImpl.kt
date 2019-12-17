package com.mosl.mobile.login

import com.google.gson.Gson
import com.mosl.mobile.app_user.helper.NewsLetterPrefStateStore
import com.mosl.mobile.app_user.helper.UserPrefStore
import com.mosl.mobile.app_user.model.User
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by lalkishore on 3/8/2017.
 */
@Singleton
class UserCacheImpl @Inject constructor(
    private val gson: Gson,
    private val userStore: UserPrefStore,
    private val newsLetterPrefStateStore: NewsLetterPrefStateStore
) : UserCache {
    override fun saveNewsLetterState(isActive: Boolean) {
        newsLetterPrefStateStore.newsLetterState.set(isActive)

    }

    override fun getNewsLetterState(): Boolean {
        return when {
            newsLetterPrefStateStore.newsLetterState.isSet -> newsLetterPrefStateStore.newsLetterState.get()

            else -> false
        }
    }

    override fun savePdf(pdfLink: String) {
        userStore.pdfLink.set(pdfLink)
    }

    override fun getPdf(): String {
        return when {
            userStore.pdfLink.isSet -> userStore.pdfLink.get()

            else -> ""
        }
    }

    override fun saveUser(user: User) {
        userStore.userInfo.set(gson.toJson(user))
    }



    override fun getUser(): User? {
        return when {
            userStore.userInfo.isSet -> gson.fromJson(
                userStore.userInfo.get(),
                User::class.java
            )
            else -> null
        }
    }

    override fun clearUser() {
        userStore.clear()
        //newsLetterPrefStateStore.clear()
    }
}
