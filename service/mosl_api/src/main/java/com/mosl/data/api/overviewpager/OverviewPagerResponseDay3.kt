package com.mosl.data.api.overviewpager

import com.google.gson.annotations.SerializedName

data class OverviewPagerResponseDay3(

    @JvmField @SerializedName("Day3")
    val Day: List<OverviewPagerResponseDayItem>
)