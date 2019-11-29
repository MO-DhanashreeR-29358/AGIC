package com.mosl.mobile.menu

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.mosl.app.coreui.base.PagerBaseFragment
import com.mosl.mobile.bytes.BytesFragment
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.overview.OverviewChildFragment
import com.mosl.mobile.overviewpager.OverviewFragment
import com.mosl.mobile.panel.PanelChildFragment
import com.mosl.mobile.panelpager.PanelFragment
import com.mosl.mobile.pdf.PdfFragment
import com.mosl.mobile.schedule.ScheduleFragment

object MenuGetter {
    const val ARG_TARGET_ITEM_POSITION = PagerBaseFragment.ARG_TARGET_ITEM_POSITION
    const val PAGE_IS_SENSITIVE = "isSensitive"
    @JvmField
    val ARG_SUB_SECTION_ID = "subSectionId"

    @SuppressLint("SwitchIntDef")
    fun getFragment(@NavigationConstants menuCode: Int): Fragment? {
        return when (menuCode) {
            NavigationConstants.NAVIGATE_TO_SCHEDULE -> ScheduleFragment()
            NavigationConstants.NAVIGATE_TO_OVERVIEW -> OverviewFragment()
            NavigationConstants.NAVIGATE_TO_PANEL -> PanelFragment()
            NavigationConstants.NAVIGATE_TO_BYTES -> BytesFragment()
            NavigationConstants.NAVIGATE_TO_PDF -> PdfFragment()
            NavigationConstants.NAVIGATE_TO_PANEL_CHILD -> PanelChildFragment()
            NavigationConstants.NAVIGATE_TO_OVERVIEW_CHILD -> OverviewChildFragment()

            else -> null
        }
    }


    @JvmStatic
    fun isHandled(@NavigationConstants pageId: Int): Boolean {
        return when (pageId) {
            NavigationConstants.NAVIGATE_TO_SCHEDULE -> true
            NavigationConstants.NAVIGATE_TO_OVERVIEW -> true
            NavigationConstants.NAVIGATE_TO_PANEL -> true
            NavigationConstants.NAVIGATE_TO_BYTES -> true
            NavigationConstants.NAVIGATE_TO_PDF -> true
            NavigationConstants.NAVIGATE_TO_PANEL_CHILD -> true
            NavigationConstants.NAVIGATE_TO_OVERVIEW_CHILD -> true
            else -> return false
        }
    }
}