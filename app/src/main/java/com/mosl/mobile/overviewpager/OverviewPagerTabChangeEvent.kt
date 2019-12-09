package com.mosl.mobile.overviewpager

import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseDayItemInfo

sealed class OverviewPagerTabChangeEvent {
    data class TabClick(var overviewDayWiseListData: List<OverviewPagerResponseDayItemInfo>) : OverviewPagerTabChangeEvent()

}

