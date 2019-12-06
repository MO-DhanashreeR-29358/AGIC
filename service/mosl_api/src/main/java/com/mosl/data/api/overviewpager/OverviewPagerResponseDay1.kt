package com.mosl.data.api.overviewpager

import com.google.gson.annotations.SerializedName

data class OverviewPagerResponseDay1(

    @JvmField @SerializedName("Day1")
    val Day: List<OverviewPagerResponseDayItem>
)