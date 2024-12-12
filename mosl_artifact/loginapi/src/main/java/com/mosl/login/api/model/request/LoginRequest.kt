package com.mosl.login.api.model.request

import com.google.gson.annotations.SerializedName

/**
 * Created by lalkishore on 6/13/2016.
 */
class LoginRequest(
    @JvmField @SerializedName("authType") val authType: String,
    @JvmField @SerializedName("clientCode") val clientCode: String,
    @JvmField @SerializedName("clientToken") val clientToken: String? = null,
    @JvmField @SerializedName("userType") val userType: String? = null,
    @JvmField @SerializedName("password") val password: String? = null,
    @JvmField @SerializedName("panDob") val panDob: String? = null,
    @JvmField @SerializedName("identifier") val identifier: String? = null,
    @JvmField @SerializedName("ipAddress") val ipAddress: String
) {
  companion object {
    const val CLIENT_CREDENTIAL = "client_credentials"
    const val SILENT_AUTH = "silent_auth"
    const val MULTI_FACTOR = "multi_factor"
  }
}
