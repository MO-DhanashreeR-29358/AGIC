package com.mosl.data.api.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleResponse (

    @JvmField @SerializedName("PDFLink")
    val pdfLink: String,

    @JvmField @SerializedName("Data")
    val data: List<ScheduleResponseItem>

)