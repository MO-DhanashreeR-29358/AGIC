package com.mosl.mobile.volunteer.domain.panel

data class PanelDayResponse (
    @JvmField  val day: String,
    @JvmField  val cRepresentiveName: String,
    @JvmField  val designation: String,
    @JvmField  val representativeStarttime: String,
    @JvmField  val representativeEndtime: String,
    @JvmField  val panelImagePath: String,
    @JvmField  val speakerIntroduction: String
)