package com.mosl.data.api.overviewpager

import com.google.gson.annotations.SerializedName

data class OverviewPagerResponseRepository(

    @JvmField @SerializedName("NAME")
    val NAME: String,

    @JvmField @SerializedName("DESIGNATION")
    val DESIGNATION: String

)