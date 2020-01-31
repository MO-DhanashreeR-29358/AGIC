package com.mosl.data.api.fcm_token

import com.google.gson.annotations.SerializedName

data class FcmTokenRequest(
  @SerializedName("fcmToken") @JvmField val deviceToken: String,
  @SerializedName("appId") @JvmField val deviceId: String,
  @SerializedName("platform") @JvmField val platform: String,
  @SerializedName("clientId") @JvmField val clientId: String
)