package com.mosl.mobile.constants

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
    NavigationConstants.NAVIGATE_TO_PANEL,
    NavigationConstants.NAVIGATE_TO_BYTES,
    NavigationConstants.NAVIGATE_TO_PDF,
    NavigationConstants.NAVIGATE_TO_OVERVIEW_CHILD,
    NavigationConstants.NAVIGATE_TO_PANEL_CHILD,
    NavigationConstants.NAVIGATE_TO_NOTES

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


    }
}
