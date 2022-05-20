package com.mosl.data.api.overviewpager

import com.google.gson.annotations.SerializedName

data class OverviewResponseData(

    @JvmField @SerializedName("Day1")
    val Day1: List<OverviewResponseDayItem>,

    @JvmField @SerializedName("Day2")
    val Day2: List<OverviewResponseDayItem>,

    @JvmField @SerializedName("Day3")
    val Day3: List<OverviewResponseDayItem>
)

data class RmOverViewResponseData(

    @JvmField @SerializedName("AGICMeeting")
    val AGICMeeting: List<RmDayWiseResponseItem>
)