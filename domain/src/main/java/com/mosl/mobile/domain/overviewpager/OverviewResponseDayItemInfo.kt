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

data class RmClientWiseResponseInfo(
    @JvmField
    var fundID: String,

    @JvmField
    var FundName: String,

    @JvmField
    var FundRepresentative: List<FundRepresentativeItemInfo>
)

data class FundRepresentativeItemInfo(
    @JvmField
    var fundRepresentativeName: String,

    @JvmField
    var meetingTimeDisplay : List<ClientWiseMeetingSlotItemInfo>
)

data class ClientWiseMeetingSlotItemInfo(
    @JvmField
    var meetingSlotTime: String,

    @JvmField
    var meeting: List<MeetingItemInfo>
)

data class MeetingItemInfo(
    @JvmField
    var empCode: String?=null,

    @JvmField
    var name: String?=null,

    @JvmField
    var corporateName: String?=null,

    @JvmField
    var roomNo: String?=null,

    @JvmField
    var contactName1: String?=null,

    @JvmField
    var startTime: String?=null,

    @JvmField
    var natureOfMeeting: String?=null,

   /* @JvmField
    var rep: List<OverviewResponseRepository>,*/

    @JvmField
    var meetingLastModified : String?=null
)