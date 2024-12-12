package com.mosl.login.api.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by lalkishore on 6/30/2016.
 */
class SignUpRequest(
    @SerializedName("mobile") private val mobile: String,
    @SerializedName("emailid") private val emailid: String,
    @SerializedName("strName") private val strName: String
)