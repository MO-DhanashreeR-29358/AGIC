package com.mosl.data.api.panel

import com.google.gson.annotations.SerializedName

data class PanelDayModelResponse(
    @JvmField @SerializedName("Day") val day: String,
    @JvmField @SerializedName("scheduled_date") val scheduledDate: String,
    @JvmField @SerializedName("corporate_Name") val corporateName: String,
    @JvmField @SerializedName("start_time") val startTime: String,
    @JvmField @SerializedName("end_time") val endTime: String,
    @JvmField @SerializedName("dayid") val dayId: Int,
    @JvmField @SerializedName("Nature_of_meeting") val natureOfMeeting: String,
    @JvmField @SerializedName("Rep") val rep: String,
    @JvmField @SerializedName("CompanyName") val companyName: String,
    @JvmField @SerializedName("CRepresentiveName") val cRepresentiveName: String,
    @JvmField @SerializedName("PanelImagePath") val panelImagePath: String,
    @JvmField @SerializedName("SpeakerIntroduction") val speakerIntroduction: String,
    @JvmField @SerializedName("Designation") val designation: String,
    @JvmField @SerializedName("RepresentativeDate") val representativeDate: String,
    @JvmField @SerializedName("RepresentativeStarttime") val representativeStarttime: String,
    @JvmField @SerializedName("RepresentativeEndtime") val representativeEndtime: String
)