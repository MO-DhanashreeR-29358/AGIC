package com.mosl.data.api.attendance

import com.google.gson.annotations.SerializedName

data class SubmitClientAttendanceRequest(
    @JvmField @SerializedName("UserId")
    val userId: String,

    @JvmField @SerializedName("ClientID")
    val clientId: Int,

    @JvmField @SerializedName("FundID")
    val fundId: Int,

    @JvmField @SerializedName("CompanyID")
    val companyId: String,

    @JvmField @SerializedName("Status")
    val status: String,

    @JvmField @SerializedName("MeetingID")
    val meetingId: Int,

    @JvmField @SerializedName("StartTime")
    val startTime: String,

    @JvmField @SerializedName("EndTime")
    val endTime: String,

    @JvmField @SerializedName("IsAllowed")
    val isAllowed: Int
)