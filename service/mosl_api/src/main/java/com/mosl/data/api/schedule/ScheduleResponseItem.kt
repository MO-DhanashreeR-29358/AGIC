package com.mosl.data.api.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleResponseItem(

    @JvmField @SerializedName("Rep")
    val Rep: List<ScheduleResponseItem_Rep>,

    @JvmField @SerializedName("record_no")
    val record_no: String,

    @JvmField @SerializedName("ReportURL")
    val ReportURL: List<String>,

    @JvmField @SerializedName("company_name")
    val company_name: String,

    @JvmField @SerializedName("room_no")
    val room_no: String,

    @JvmField @SerializedName("Schedule_Date")
    val Schedule_Date: String,

    @JvmField @SerializedName("fund_id")
    val fund_id: Int,

    @JvmField @SerializedName("start_time")
    val start_time: String,

    @JvmField @SerializedName("end_time")
    val end_time: String,

    @JvmField @SerializedName("Nature_of_meeting")
    val Nature_of_meeting: String,

    @JvmField @SerializedName("updateflag")
    val updateflag: String,

    @JvmField @SerializedName("@UpdateDate")
    val UpdateDate: String

)