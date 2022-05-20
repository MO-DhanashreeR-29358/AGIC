package com.mosl.data.api.overviewpager

import com.google.gson.annotations.SerializedName

data class OverviewResponseDayItem(

    @JvmField @SerializedName("Day")
    val day: String,

    @JvmField @SerializedName("scheduled_date")
    val scheduledDate: String,

    @JvmField @SerializedName("corporate_Name")
    val corporateName: String,

    @JvmField @SerializedName("start_time")
    val startTime: String,

    @JvmField @SerializedName("end_time")
    val endTime: String,

    @JvmField @SerializedName("dayid")
    val dayid: String,

    @JvmField @SerializedName("Nature_of_meeting")
    val natureOfMeeting: String,

    @JvmField @SerializedName("Rep")
    val rep: List<OverviewResponseRepository>,

    @JvmField @SerializedName("CompanyName")
    val companyName: String,

    @JvmField @SerializedName("CRepresentiveName")
    val cRepresentiveName: String,

    @JvmField @SerializedName("PanelImagePath")
    val panelImagePath: String,

    @JvmField @SerializedName("SpeakerIntroduction")
    val speakerIntroduction: String,

    @JvmField @SerializedName("Designation")
    val designation: String,

    @JvmField @SerializedName("RepresentativeDate")
    val representativeDate: String,

    @JvmField @SerializedName("RepresentativeStarttime")
    val representativeStartTime: String,

    @JvmField @SerializedName("RepresentativeEndtime")
    val representativeEndTime: String
)

data class RmDayWiseResponseItem(
    @JvmField
    @SerializedName("MeetingDay")
    var day : String,

    @JvmField
    @SerializedName("MeetingSlot")
    var meetingTimeDisplay : List<MeetingSlot>,
)

data class MeetingSlot(
    @JvmField
    @SerializedName("MeetingSlotTime")
    var meetingSlotTime: String,

    @JvmField
    @SerializedName("Meeting")
    var meeting: List<MeetingTimeListItem>
)

data class MeetingTimeListItem(
    @JvmField
    @SerializedName("Empcode")
    var empCode: String?=null,

    @JvmField
    @SerializedName("Name")
    var name: String?=null,

    @JvmField
    @SerializedName("fund_id")
    var fundId: String?=null,

    @JvmField
    @SerializedName("fund_name")
    var fundName: String?=null,

    @JvmField
    @SerializedName("client_id")
    var clientId: String?=null,

    @JvmField
    @SerializedName("client_name")
    var clientName: String?=null,

    @JvmField
    @SerializedName("company_name")
    var companyName: String?=null,

    @JvmField
    @SerializedName("room_no")
    var roomNo: String?=null,

    @JvmField
    @SerializedName("contact_name1")
    var contactName1: String?=null,

    @JvmField
    @SerializedName("start_time")
    var startTime: String?=null,

    @JvmField
    @SerializedName("Nature_of_meeting")
    var natureOfMeeting: String?=null,

    @JvmField
    @SerializedName("rep")
    var rep: List<OverviewResponseRepository>,

    @JvmField
    @SerializedName("MeetingLastModified" )
    var meetingLastModified : String?=null
)