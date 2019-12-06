package com.mosl.data.api.overviewpager

import com.google.gson.annotations.SerializedName

data class OverviewPagerResponseDayItem(

    @JvmField @SerializedName("Day")
    val Day: String,

    @JvmField @SerializedName("scheduled_date")
    val scheduled_date: String,

    @JvmField @SerializedName("corporate_Name")
    val corporate_Name: String,

    @JvmField @SerializedName("start_time")
    val start_time: String,

    @JvmField @SerializedName("end_time")
    val end_time: String,

    @JvmField @SerializedName("dayid")
    val dayid: String,

    @JvmField @SerializedName("Nature_of_meeting")
    val Nature_of_meeting: String,

    @JvmField @SerializedName("Rep")
    val Rep: List<OverviewPagerResponseRepository>,

    @JvmField @SerializedName("CompanyName")
    val CompanyName: String,

    @JvmField @SerializedName("CRepresentiveName")
    val CRepresentiveName: String,

    @JvmField @SerializedName("PanelImagePath")
    val PanelImagePath: String,

    @JvmField @SerializedName("SpeakerIntroduction")
    val SpeakerIntroduction: String,

    @JvmField @SerializedName("Designation")
    val Designation: String,

    @JvmField @SerializedName("RepresentativeDate")
    val RepresentativeDate: String,

    @JvmField @SerializedName("RepresentativeStarttime")
    val RepresentativeStarttime: String,

    @JvmField @SerializedName("RepresentativeEndtime")
    val RepresentativeEndtime: String
)