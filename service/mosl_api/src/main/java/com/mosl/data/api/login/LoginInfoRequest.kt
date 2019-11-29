package com.mosl.data.api.login

import com.google.gson.annotations.SerializedName

data class LoginInfoRequest(

  @JvmField @SerializedName("UniqueID")
   val uniqueId: String,
  @JvmField @SerializedName("DeviceID")
   val deviceId: String
)