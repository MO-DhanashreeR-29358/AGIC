package com.mosl.login.api.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by lalkishore on 14/01/18.
 */
class TokenResponse(
    @JvmField @SerializedName("accessToken") val accessToken: String,
    @JvmField @SerializedName("expiresIn") val expiresIn: Int
)