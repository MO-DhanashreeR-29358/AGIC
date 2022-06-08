package com.mosl.data.api.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleResponseItem(

    @JvmField @SerializedName("Rep")
    val Rep: List<ScheduleResponseItemRep>,

    @JvmField @SerializedName("record_no")
    val recordNo: String,

    @JvmField @SerializedName("ReportURL")
    val ReportURL: List<String>,

    @JvmField @SerializedName("company_name")
    val companyName: String,

    @JvmField @SerializedName("room_no")
    val roomNo: String,

    @JvmField @SerializedName("Schedule_Date")
    val ScheduleDate: String,

    @JvmField @SerializedName("fund_id")
    val fundId: Int,

    @JvmField @SerializedName("start_time")
    val startTime: String,

    @JvmField @SerializedName("end_time")
    val endTime: String,

    @JvmField @SerializedName("Nature_of_meeting")
    val NatureOfMeeting: String,

    @JvmField @SerializedName("updateflag")
    val updateFlag: String,

    @JvmField @SerializedName("@UpdateDate")
    val UpdateDate: String

)