package com.mosl.data.api.overviewpager

import com.google.gson.annotations.SerializedName

data class OverviewPagerResponseDay2(

    @JvmField @SerializedName("Day2")
    val Day: List<OverviewPagerResponseDayItem>
)