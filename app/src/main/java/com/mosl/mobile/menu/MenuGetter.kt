package com.mosl.mobile.menu

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.mosl.mobile.bytes.BytesFragment
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.corporatepager.CorporatePagerFragment
import com.mosl.mobile.notes.NotesFragment
import com.mosl.mobile.notification.NotificationHistoryFragment
import com.mosl.mobile.overview.OverviewChildFragment
import com.mosl.mobile.overviewpager.OverviewFragment
import com.mosl.mobile.panel.PanelChildFragment
import com.mosl.mobile.panelpager.PanelFragment
import com.mosl.mobile.pdf.PdfFragment
import com.mosl.mobile.schedule.ScheduleFragment

object MenuGetter {
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
            NavigationConstants.NAVIGATE_TO_CORPORATE_SCHEDULE -> CorporatePagerFragment()
            NavigationConstants.NAVIGATE_TO_NOTES -> NotesFragment()
            NavigationConstants.NAVIGATE_TO_NOTIFICATION -> NotificationHistoryFragment()

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
            NavigationConstants.NAVIGATE_TO_NOTES -> true
            NavigationConstants.NAVIGATE_TO_CORPORATE_SCHEDULE -> true
            else -> return false
        }
    }
}