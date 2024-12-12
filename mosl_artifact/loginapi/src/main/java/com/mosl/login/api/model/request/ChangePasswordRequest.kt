package com.mosl.login.api.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by ankitnagda on 10/4/2016.
 */
class ChangePasswordRequest(
    @SerializedName("userCode") private val userCode: String,
    @SerializedName("oldPassword") private val oldPassword: String?,
    @SerializedName("newPassword") private val newPassword: String?,
    @SerializedName("userType") private val userType: String,
    @SerializedName("reason") private val reason: String
)