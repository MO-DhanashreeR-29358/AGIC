package com.mosl.data.api.attendance

import com.google.gson.annotations.SerializedName

data class GetClientMeetingRequest(
    @JvmField
    @SerializedName("UniqueId")
    val uniqueId: String,

    @JvmField
    @SerializedName("CompanyId")
    val companyId: String
)