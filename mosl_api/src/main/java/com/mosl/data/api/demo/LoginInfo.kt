package com.mosl.data.api.demo

import com.google.gson.annotations.SerializedName

data class LoginInfo(
  @JvmField @SerializedName("authType")
  var authType: String,
  @JvmField @SerializedName("password")
  var password: String,
  @JvmField @SerializedName("employeeId")
  var employeeId: String,
  @JvmField @SerializedName("ipAddress")
  var ipAddress: String

)