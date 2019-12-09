package com.mosl.mobile.domain.overviewpager

data class OverviewPagerResponseDayItemInfo(

    val Day: String,
    val scheduled_date: String,
    val corporate_Name: String,
    val start_time: String,
    val end_time: String,
    val dayid: String,
    val Nature_of_meeting: String,
    val Rep: List<OverviewPagerResponseRepositoryInfo>,
    val CompanyName: String?,
    val CRepresentiveName: String?,
    val PanelImagePath: String?,
    val SpeakerIntroduction: String?,
    val Designation: String?,
    val RepresentativeDate: String,
    val RepresentativeStarttime: String,
    val RepresentativeEndtime: String
)