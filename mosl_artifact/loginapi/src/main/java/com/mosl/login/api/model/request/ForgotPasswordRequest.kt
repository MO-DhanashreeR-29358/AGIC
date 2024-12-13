package com.mosl.login.api.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by lalkishore on 7/31/2017.
 */

class ForgotPasswordRequest(
    @SerializedName("value") private val userCode: String,
    @SerializedName("pan") private val emailId: String
)
