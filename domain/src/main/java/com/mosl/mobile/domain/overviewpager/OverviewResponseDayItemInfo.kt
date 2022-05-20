package com.mosl.mobile.domain.overviewpager

data class OverviewResponseDayItemInfo(

    @JvmField val day: String,

    @JvmField val scheduledDate: String,

    @JvmField val corporateName: String,

    @JvmField val startTime: String,

    @JvmField val endTime: String,

    @JvmField val dayid: String,

    @JvmField val natureOfMeeting: String,

    @JvmField var rep: List<OverviewResponseRepresntativeInfo>,

    @JvmField val companyName: String?,

    @JvmField val cRepresentiveName: String?,

    @JvmField val panelImagePath: String?,

    @JvmField val speakerIntroduction: String?,

    @JvmField val designation: String?,

    @JvmField val representativeDate: String,

    @JvmField val representativeStartTime: String,

    @JvmField val representativeEndTime: String,

    @JvmField var viewType: Int = 1,

    @JvmField var isExpanded: Boolean = false,
)

data class RmOverViewMeetingResponseInfo(
    @JvmField
    var meetingTime: String,

    @JvmField
    var meetingList: List<RmOverviewResponseDayItemInfo>
)

data class RmOverviewResponseDayItemInfo(
    @JvmField
    var empCode: String,

    @JvmField
    var name: String,

    @JvmField
    var fundId: String,

    @JvmField
    var fundName: String,

    @JvmField
    var clientId: String,

    @JvmField
    var clientName: String,

    @JvmField
    var companyName: String,

    @JvmField
    var roomNo: String,

    @JvmField
    var contactName1: String,

    @JvmField
    var startTime: String,

    @JvmField
    var natureOfMeeting: String
)