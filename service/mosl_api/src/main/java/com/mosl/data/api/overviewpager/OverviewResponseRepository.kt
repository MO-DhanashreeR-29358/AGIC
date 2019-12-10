package com.mosl.data.api.overviewpager

import com.google.gson.annotations.SerializedName

data class OverviewResponseRepository(

    @JvmField @SerializedName("NAME")
    val name: String,

    @JvmField @SerializedName("DESIGNATION")
    val designation: String

)