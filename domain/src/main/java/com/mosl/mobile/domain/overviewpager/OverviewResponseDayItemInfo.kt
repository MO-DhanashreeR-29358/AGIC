package com.mosl.mobile.domain.overviewpager

data class OverviewResponseDayItemInfo(

    @JvmField val day: String,

    @JvmField val scheduledDate: String,

    @JvmField val corporateName: String,

    @JvmField val startTime: String,

    @JvmField val endTime: String,

    @JvmField val dayid: String,

    @JvmField val natureOfMeeting: String,

    @JvmField val rep: List<OverviewResponseRepresntativeInfo>,

    @JvmField val companyName: String?,

    @JvmField val cRepresentiveName: String?,

    @JvmField val panelImagePath: String?,

    @JvmField val speakerIntroduction: String?,

    @JvmField val designation: String?,

    @JvmField val representativeDate: String,

    @JvmField val representativeStartTime: String,

    @JvmField val representativeEndTime: String,

    @JvmField val roomNo: String,
)