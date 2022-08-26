package com.mosl.data.api.login

import com.google.gson.annotations.SerializedName

data class LoginInfoRequest(

//  @JvmField @SerializedName("UniqueID")
  @JvmField @SerializedName("EmpId")
   val uniqueId: String,
  @JvmField @SerializedName("ConfDate")
   val deviceId: String
)