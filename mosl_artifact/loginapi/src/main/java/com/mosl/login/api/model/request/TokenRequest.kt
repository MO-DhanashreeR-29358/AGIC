package com.mosl.login.api.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by lalkishore on 7/31/2017.
 */

class TokenRequest(
    @SerializedName("accessToken") private val accessToken: String,
    @SerializedName("refreshToken") private val refreshToken: String,
    @SerializedName("clientCode") private val clientCode: String
)
