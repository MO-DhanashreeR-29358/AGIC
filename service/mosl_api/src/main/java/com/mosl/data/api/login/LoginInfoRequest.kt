package com.mosl.data.api.login

import com.google.gson.annotations.SerializedName

data class LoginInfoRequest(

    @JvmField @SerializedName("EmpId")
    val empId: String,

    @JvmField @SerializedName("Password")
    val password: String,

    @JvmField @SerializedName("ConfDate")
    val confDate: String
)