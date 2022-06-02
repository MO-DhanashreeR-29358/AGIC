package com.mosl.data.api.login

import com.google.gson.annotations.SerializedName

data class LoginInfoResponse(

    @JvmField @SerializedName("client_id") val clientId: String,
    @JvmField @SerializedName("fund_id") val fundId: String,
    @JvmField @SerializedName("client_name") val clientName: String,
    @JvmField @SerializedName("EmailID") val emailId: String,
    @JvmField @SerializedName("fund_name") val fundName: String,
    @JvmField @SerializedName("contact_name1") val contactName1: String,
    @JvmField @SerializedName("contact_name2") val contactName2: String,
    @JvmField @SerializedName("contact_name3") val contactName3: String,
    @JvmField @SerializedName("contact_mobile1") val contactMobile1: String,
    @JvmField @SerializedName("contact_mobile2") val contactMobile2: String,
    @JvmField @SerializedName("contact_mobile3") val contactMobile3: String,
    @JvmField @SerializedName("day") val day: String,
    @JvmField @SerializedName("role") val role: String

)

data class RmDataResponse(
    @JvmField @SerializedName("Empcode") val empCode: String?=null,
    @JvmField @SerializedName("Name") val name: String?=null,
    @JvmField @SerializedName("fund_id") val fundId: String?=null,
    @JvmField @SerializedName("fund_name") val fundName: String?=null,
    @JvmField @SerializedName("room_no") val roomNO: String?=null,
    @JvmField @SerializedName("company_name") val companyName: String?=null,
    @JvmField @SerializedName("contact_name1") val contactName1: String?,
    @JvmField @SerializedName("contact_name2") val contactName2: String?,
    @JvmField @SerializedName("contact_name3") val contactName3: String?
)

