package com.mosl.mobile.volunteer.menu

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import com.mosl.mobile.volunteer.bytes.BytesFragment
import com.mosl.mobile.volunteer.constants.NavigationConstants
import com.mosl.mobile.volunteer.corporatepager.CorporatePagerFragment
import com.mosl.mobile.volunteer.feedback.FeedbackFragment
import com.mosl.mobile.volunteer.notes.NotesFragment
import com.mosl.mobile.volunteer.notification.NotificationHistoryFragment
import com.mosl.mobile.volunteer.overview.OverviewChildFragment
import com.mosl.mobile.volunteer.overviewpager.OverviewFragment
import com.mosl.mobile.volunteer.panel.PanelChildFragment
import com.mosl.mobile.volunteer.panelpager.PanelFragment
import com.mosl.mobile.volunteer.pdf.PdfFragment
import com.mosl.mobile.volunteer.rmclientwise.RmClientWiseFragment
import com.mosl.mobile.volunteer.rmclientwisepager.RmClientOverviewFragment
import com.mosl.mobile.volunteer.schedule.ScheduleFragment

object MenuGetter {
    @JvmField
    val ARG_SUB_SECTION_ID = "subSectionId"

    @SuppressLint("SwitchIntDef")
    fun getFragment(@NavigationConstants menuCode: Int): Fragment? {
        return when (menuCode) {
            NavigationConstants.NAVIGATE_TO_SCHEDULE -> ScheduleFragment()
            NavigationConstants.NAVIGATE_TO_OVERVIEW -> OverviewFragment()
            NavigationConstants.NAVIGATE_TO_RM_CLIENT_OVERVIEW -> RmClientOverviewFragment()
            NavigationConstants.NAVIGATE_TO_PANEL -> PanelFragment()
            NavigationConstants.NAVIGATE_TO_BYTES -> BytesFragment()
            NavigationConstants.NAVIGATE_TO_PDF -> PdfFragment()
            NavigationConstants.NAVIGATE_TO_PANEL_CHILD -> PanelChildFragment()
            NavigationConstants.NAVIGATE_TO_OVERVIEW_CHILD -> OverviewChildFragment()
            NavigationConstants.NAVIGATE_TO_RM_CLIENT_CHILD -> RmClientWiseFragment()
            NavigationConstants.NAVIGATE_TO_CORPORATE_SCHEDULE -> CorporatePagerFragment()
            NavigationConstants.NAVIGATE_TO_NOTES -> NotesFragment()
            NavigationConstants.NAVIGATE_TO_NOTIFICATION -> NotificationHistoryFragment()
            NavigationConstants.NAVIGATE_TO_FEEDBACK -> FeedbackFragment()

            else -> null
        }
    }


    @JvmStatic
    fun isHandled(@NavigationConstants pageId: Int): Boolean {
        return when (pageId) {
            NavigationConstants.NAVIGATE_TO_SCHEDULE -> true
            NavigationConstants.NAVIGATE_TO_OVERVIEW -> true
            NavigationConstants.NAVIGATE_TO_RM_CLIENT_OVERVIEW -> true
            NavigationConstants.NAVIGATE_TO_PANEL -> true
            NavigationConstants.NAVIGATE_TO_BYTES -> true
            NavigationConstants.NAVIGATE_TO_PDF -> true
            NavigationConstants.NAVIGATE_TO_PANEL_CHILD -> true
            NavigationConstants.NAVIGATE_TO_OVERVIEW_CHILD -> true
            NavigationConstants.NAVIGATE_TO_NOTES -> true
            NavigationConstants.NAVIGATE_TO_CORPORATE_SCHEDULE -> true
            NavigationConstants.NAVIGATE_TO_NOTIFICATION -> true
            NavigationConstants.NAVIGATE_TO_FEEDBACK -> true
            else -> return false
        }
    }
}