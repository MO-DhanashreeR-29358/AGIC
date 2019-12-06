package com.mosl.data.api.overviewpager

import com.google.gson.annotations.SerializedName

data class OverviewPagerRequest (
    @JvmField @SerializedName("ClientID")
    val ClientID: String,

    @JvmField @SerializedName("Over")
    val Over: String

)