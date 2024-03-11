package com.mosl.mobile.volunteer.constants

import androidx.annotation.IntDef
import kotlin.annotation.AnnotationRetention.SOURCE

/**
 * Created by ankitnagda on 3/23/2017.
 */

@kotlin.annotation.Retention(SOURCE)
@IntDef(
    NavigationConstants.NAVIGATE_TO_PORTFOLIO,
    NavigationConstants.NAVIGATE_TO_NONE,
    NavigationConstants.NAVIGATE_TO_SCHEDULE,
    NavigationConstants.NAVIGATE_TO_OVERVIEW,
    NavigationConstants.NAVIGATE_TO_RM_CLIENT_OVERVIEW,
    NavigationConstants.NAVIGATE_TO_PANEL,
    NavigationConstants.NAVIGATE_TO_BYTES,
    NavigationConstants.NAVIGATE_TO_PDF,
    NavigationConstants.NAVIGATE_TO_OVERVIEW_CHILD,
    NavigationConstants.NAVIGATE_TO_RM_CLIENT_CHILD,
    NavigationConstants.NAVIGATE_TO_PANEL_CHILD,
    NavigationConstants.NAVIGATE_TO_NOTES,
    NavigationConstants.NAVIGATE_TO_CORPORATE_SCHEDULE,
    NavigationConstants.NAVIGATE_TO_NOTIFICATION,
    NavigationConstants.NAVIGATE_TO_FEEDBACK,
    NavigationConstants.NAVIGATE_TO_CORPORATE_LIST_FRAGMENT,
    NavigationConstants.NAVIGATE_TO_QR_CODE_SCAN_FRAGMENT,
    NavigationConstants.NAVIGATE_TO_ATTENDANCE_DETAILS_FRAGMENT
)
annotation class NavigationConstants {
    companion object {
        const val NAVIGATE_TO_PORTFOLIO = 101
        const val NAVIGATE_TO_NONE = -1
        const val NAVIGATE_TO_SCHEDULE = 1
        const val NAVIGATE_TO_OVERVIEW = 2
        const val NAVIGATE_TO_PANEL = 3
        const val NAVIGATE_TO_BYTES = 4
        const val NAVIGATE_TO_PDF = 5
        const val NAVIGATE_TO_OVERVIEW_CHILD = 6
        const val NAVIGATE_TO_PANEL_CHILD = 7
        const val NAVIGATE_TO_NOTES = 8
        const val NAVIGATE_TO_NOTIFICATION = 9
        const val NAVIGATE_TO_CORPORATE_SCHEDULE = 10
        const val NAVIGATE_TO_FEEDBACK = 11
        const val NAVIGATE_TO_RM_CLIENT_OVERVIEW = 12
        const val NAVIGATE_TO_RM_CLIENT_CHILD = 13
        const val NAVIGATE_TO_CORPORATE_LIST_FRAGMENT = 14
        const val NAVIGATE_TO_QR_CODE_SCAN_FRAGMENT = 15
        const val NAVIGATE_TO_ATTENDANCE_DETAILS_FRAGMENT = 16
    }
}
