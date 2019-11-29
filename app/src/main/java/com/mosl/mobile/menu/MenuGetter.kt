package com.mosl.mobile.menu

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.mosl.app.coreui.base.LeanBaseFragment
import com.mosl.app.coreui.base.PagerBaseFragment
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.demo.DemoFragment
import com.mosl.mobile.overview.OverViewFragment
import com.mosl.mobile.panel.PanelFragment
import com.mosl.mobile.schedule.ScheduleFragment

object MenuGetter {
  const val ARG_TARGET_ITEM_POSITION = PagerBaseFragment.ARG_TARGET_ITEM_POSITION
  const val PAGE_IS_SENSITIVE = "isSensitive"

  @SuppressLint("SwitchIntDef")
  fun getFragment(@NavigationConstants menuCode: Int): Fragment? {
    return when (menuCode) {
      NavigationConstants.NAVIGATE_TO_SCHEDULE->ScheduleFragment()
      NavigationConstants.NAVIGATE_TO_OVERVIEW->OverViewFragment()
      NavigationConstants.NAVIGATE_TO_PANEL->PanelFragment()
      NavigationConstants.NAVIGATE_TO_BYTES->ScheduleFragment()
      NavigationConstants.NAVIGATE_TO_PDF->ScheduleFragment()
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
      else -> return false
    }
  }
}