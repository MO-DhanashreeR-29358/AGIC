package com.mosl.data.api

import com.google.gson.annotations.SerializedName

data class BaseResponseAgic<T>(
    @SerializedName("Message") private val msg: String?,
    @JvmField @SerializedName("Response") val success: String?,
    @JvmField @SerializedName("IsActive") val isActive: String,
    @JvmField @SerializedName("Data") val data: T?,
    @JvmField @SerializedName("PDFLink") val pdf: String? = null,
    @JvmField @SerializedName("notificationPending") val notificationPending: Int,
    @JvmField @SerializedName("feedbackPending") val feedbackPending: Boolean
) {
    val isSuccessful: Boolean
        get() = success == "true" && data != null

    val message: String
        get() = msg ?: "An Error Occurred"

    inline fun onSuccess(action: (data: T) -> Unit) {
        if (success == "true" && data != null) action(data)
    }

    val IsActive: Boolean
        get() = isActive.equals("Y")

    val IsSucess: Boolean
        get() = success.equals("true", ignoreCase = true)


}