package com.mosl.login.api.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by lalkishore on 7/31/2017.
 */

class AuthProfile(
    @JvmField @SerializedName("accessToken") val accessToken: String? = null,
    @JvmField @SerializedName("refreshToken") val refreshToken: String? = null,
    @JvmField @SerializedName("expiresIn") val expiresIn: Int,
    @JvmField @SerializedName("reLoginAfter") val reLoginAfter: Int,
    @JvmField @SerializedName("userInfo") val userInfo: UserProfile,
    @JvmField @SerializedName("versioNo") val versionNo: Int
)
