package com.mosl.api.core

import com.google.gson.annotations.SerializedName

/**
 * Created by lalkishore on 13/01/18.
 */

data class BaseResponse<T>(
    @SerializedName("message") private val msg: String?,
    @JvmField @SerializedName("success") val success: Boolean,
    @JvmField @SerializedName("data") val data: T?
) {
  val isSuccessful: Boolean
    get() = success && data != null

  val message: String
    get() = msg ?: "An Error Occurred"

  inline fun onSuccess(action: (data: T) -> Unit) {
    if (success && data != null) action(data)
  }
}
