package com.mosl.volunteer.dashboard

data class DashboardHeader(
    @JvmField val title: String,
    @JvmField val screens: List<DashboardData>
)

data class DashboardData(
    @JvmField val title: String,
    @JvmField val pageId: Int
)