package com.mosl.data.api.panel

import com.google.gson.annotations.SerializedName

data class PanelDataResponse (

    @JvmField @SerializedName("Day1") val dayModel1: List<PanelDayModelResponse>,
    @JvmField @SerializedName("Day2") val dayModel2: List<PanelDayModelResponse>,
    @JvmField @SerializedName("Day3") val dayModel3: List<PanelDayModelResponse>
)