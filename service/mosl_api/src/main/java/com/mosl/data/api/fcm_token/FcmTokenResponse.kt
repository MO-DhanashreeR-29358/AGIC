package com.mosl.data.api.fcm_token

import com.google.gson.annotations.SerializedName

data class FcmTokenResponse(

  @SerializedName("Response")
  val response: String,

  @SerializedName("Data")
  val type: String,
  @SerializedName("Message")
  val message: String

) {
  fun isSuccess(): Boolean {
    return response.equals("true")
  }
}