package com.mosl.mobile.volunteer.appuser.login

import com.google.gson.Gson
import com.mosl.data.api.attendance.ClientMeetingDetailsModel
import com.mosl.mobile.volunteer.appuser.helper.ClientPrefStore
import com.mosl.mobile.volunteer.appuser.helper.NewsLetterPrefStateStore
import com.mosl.mobile.volunteer.appuser.helper.UserPrefStore
import com.mosl.mobile.volunteer.appuser.model.Volunteer
import com.mosl.mobile.volunteer.login.UserCache
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

    override fun getClientDetailsModel(): ClientMeetingDetailsModel? {
        return when {
            clientPrefStore.clientPref.isSet -> gson.fromJson(
                clientPrefStore.clientPref.get(),
                ClientMeetingDetailsModel::class.java
            )
            else -> null
        }
    }

    override fun saveClientDetailsModel(model: ClientMeetingDetailsModel) {
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
        userStore.userInfo.set(gson.toJson(user))
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
