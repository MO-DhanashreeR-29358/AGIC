package com.mosl.data.api.overviewpager

import com.google.gson.annotations.SerializedName

data class OverviewPagerResponseData(

    @JvmField @SerializedName("Day1")
    val Day1: List<OverviewPagerResponseDayItem>,

    @JvmField @SerializedName("Day2")
    val Day2: List<OverviewPagerResponseDayItem>,

    @JvmField @SerializedName("Day3")
    val Day3: List<OverviewPagerResponseDayItem>
)