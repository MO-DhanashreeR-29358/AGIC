package com.mosl.mobile.volunteer.appuser.login

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mosl.data.api.attendance.ClientDetails
import com.mosl.mobile.volunteer.appuser.helper.ClientPrefStore
import com.mosl.mobile.volunteer.appuser.helper.NewsLetterPrefStateStore
import com.mosl.mobile.volunteer.appuser.helper.UserPrefStore
import com.mosl.mobile.volunteer.appuser.model.Volunteer
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by lalkishore on 3/8/2017.
 */
@Singleton
class UserCacheImpl @Inject constructor(
    private val gson: Gson,
    private val userStore: UserPrefStore,
    private val newsLetterPrefStateStore: NewsLetterPrefStateStore,
    private val clientPrefStore: ClientPrefStore
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

    override fun getClientDetailsModel(): ClientDetails? {
        return when {
            clientPrefStore.clientPref.isSet -> gson.fromJson(
                clientPrefStore.clientPref.get(),
                ClientDetails::class.java
            )
            else -> null
        }
    }

    override fun saveClientDetailsModel(model: ClientDetails) {
        clientPrefStore.clientPref.set(gson.toJson(model))
    }

    override fun clearClientDetailsModel() {
        clientPrefStore.clear()
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

    override fun saveUser(user: Volunteer) {
        try {
            userStore.userInfo.set(gson.toJson(user))
        }catch (e: JsonSyntaxException){

        }

    }

    override fun getUser(): Volunteer? {
        return when {
            userStore.userInfo.isSet -> gson.fromJson(
                userStore.userInfo.get(),
                Volunteer::class.java
            )
            else -> null
        }
    }

    override fun clearUser() {
        userStore.clear()
    }
}
