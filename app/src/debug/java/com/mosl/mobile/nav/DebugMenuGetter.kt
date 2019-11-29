package com.mosl.mobile.nav

import androidx.fragment.app.Fragment
import com.mosl.mobile.dashboard.DashboardFragment
import com.mosl.mobile.drawermenu.DrawerMenuFragment
import com.mosl.mobile.featurescope.FirstPlaygroundFragment
import com.mosl.mobile.featurescope.SecondPlaygroundFragment

object DebugMenuGetter {
  @JvmStatic
  fun getFragment(@DebugConstants pageId: Int): Fragment? {
    return when (pageId) {
      DebugConstants.NAVIGATE_TO_DASHBOARD -> DashboardFragment()
      DebugConstants.NAVIGATE_TO_DRAWER_MENU -> DrawerMenuFragment()
      DebugConstants.NAVIGATE_TO_PLAYGROUND_01 -> FirstPlaygroundFragment()
      DebugConstants.NAVIGATE_TO_PLAYGROUND_02 -> SecondPlaygroundFragment()
      else -> null
    }
  }
  @JvmStatic
  fun isHandled(@DebugConstants pageId: Int): Boolean {
    return when (pageId) {
      DebugConstants.NAVIGATE_TO_DASHBOARD -> true
      DebugConstants.NAVIGATE_TO_DRAWER_MENU -> true
      DebugConstants.NAVIGATE_TO_PLAYGROUND_01 -> true
      DebugConstants.NAVIGATE_TO_PLAYGROUND_02 -> true
      else -> false
    }
  }
}