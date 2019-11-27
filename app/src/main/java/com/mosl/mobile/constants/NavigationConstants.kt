package com.mosl.mobile.constants

import androidx.annotation.IntDef
import kotlin.annotation.AnnotationRetention.SOURCE

/**
 * Created by ankitnagda on 3/23/2017.
 */

@kotlin.annotation.Retention(SOURCE)
@IntDef(
    NavigationConstants.NAVIGATE_TO_PORTFOLIO,
    NavigationConstants.NAVIGATE_TO_BYTES,
    NavigationConstants.NAVIGATE_TO_PANEL_BASE,
    NavigationConstants.NAVIGATE_TO_NONE
)
annotation class NavigationConstants {
    companion object {
        const val NAVIGATE_TO_PORTFOLIO = 101
        const val NAVIGATE_TO_BYTES = 102
        const val NAVIGATE_TO_PANEL_BASE = 103

        const val NAVIGATE_TO_NONE = -1
    }
}
