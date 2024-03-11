package com.mosl.data.api.attendance

import com.google.gson.annotations.SerializedName

data class ClientDetails(
    @JvmField @SerializedName("UserID")
    val userId: String?,

    @JvmField @SerializedName("ClientID")
    val clientId: Int?,

    @JvmField @SerializedName("ClientName")
    val clientName: String?,

    @JvmField @SerializedName("FundID")
    val fundId: Int?,

    @JvmField @SerializedName("FundName")
    val fundName: String?,

    @JvmField @SerializedName("CompanyID")
    val companyId: String?,

    @JvmField @SerializedName("CompanyName")
    val companyName: String?,

    @JvmField @SerializedName("Status")
    val status: String?,

    @JvmField @SerializedName("MeetingID")
    val meetingId: Int?,

    @JvmField @SerializedName("StartTime")
    val startTime: String?,

    @JvmField @SerializedName("EndTime")
    val endTime: String?,

    @JvmField @SerializedName("Message")
    val message: String?,

    @JvmField @SerializedName("IsAllowed")
    val isAllowed: Int?
)
