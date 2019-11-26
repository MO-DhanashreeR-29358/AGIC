package com.mosl.mobile.coredata.util

import com.google.gson.annotations.SerializedName

data class BaseResponseDemo<T>(
  @SerializedName("message") private val msg: String?,
  @JvmField @SerializedName("isSuccessful") val success: Boolean,
  @JvmField @SerializedName("user") val data: T?
) {
  val isSuccessful: Boolean
    get() = success && data != null

  val message: String
    get() = msg ?: "An Error Occurred"

  inline fun onSuccess(action: (data: T) -> Unit) {
    if (success && data != null) action(data)
  }
}