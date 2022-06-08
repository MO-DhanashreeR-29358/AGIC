package com.mosl.data.api.fcmtoken

import com.google.gson.annotations.SerializedName

data class FcmTokenRequest(
  @SerializedName("fcmToken") @JvmField val deviceToken: String,
  @SerializedName("appId") @JvmField val deviceId: String,
  @SerializedName("platform") @JvmField val platform: String,
  @SerializedName("clientId") @JvmField val clientId: String
)