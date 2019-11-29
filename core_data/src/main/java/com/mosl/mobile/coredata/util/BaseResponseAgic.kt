package com.mosl.mobile.coredata.util

import com.google.gson.annotations.SerializedName

data class BaseResponseAgic<T>(
    @JvmField  @SerializedName("Message") private val msg: String?,
    @JvmField @SerializedName("Response") val success: String?,
    @JvmField @SerializedName("IsActive") val isActive: Any,
    @JvmField @SerializedName("Data") val data: T?
) {
    val isSuccessful: Boolean
        get() = success == "true" && data != null

    val message: String
        get() = msg ?: "An Error Occurred"

    inline fun onSuccess(action: (data: T) -> Unit) {
        if (success == "true" && data != null) action(data)
    }
}