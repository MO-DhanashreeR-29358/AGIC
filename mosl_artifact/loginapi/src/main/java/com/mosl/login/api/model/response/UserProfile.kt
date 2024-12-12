package com.mosl.login.api.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by lalkishore on 2/22/2016.
 */

class UserProfile(
    @JvmField @SerializedName("userName") val userName: String,
    @JvmField @SerializedName("loginAs")  val loginAs: String,
    @JvmField @SerializedName("statusMessage") val statusMessage: String,
    @JvmField @SerializedName("userCode") val userCode: String,
    @JvmField @SerializedName("cashCommodity") val cashCommodity: String,
    @JvmField @SerializedName("token")  val token: String,
    @JvmField @SerializedName("userType") val userType: String,
    @JvmField @SerializedName("reason")  val reason: String?,
    @JvmField @SerializedName("mobile") val mobile: String?,
    @JvmField @SerializedName("email") val emailId: String?,
    @JvmField @SerializedName("isStaff") val isStaff: Boolean
) {
  val isSuccess: Boolean
    get() = "Success" == statusMessage

  val isPasswordExpired: Boolean
    get() = "PasswordExpired" == statusMessage

  val isFirstTimeLogin: Boolean
    get() = "FirstTimeLogin" == statusMessage
}