package com.mosl.mobile.menu

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.mosl.app.coreui.base.LeanBaseFragment
import com.mosl.app.coreui.base.PagerBaseFragment
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.demo.DemoFragment

object MenuGetter {
  const val ARG_TARGET_ITEM_POSITION = PagerBaseFragment.ARG_TARGET_ITEM_POSITION
  const val PAGE_IS_SENSITIVE = "isSensitive"

  @SuppressLint("SwitchIntDef")
  fun getFragment(@NavigationConstants menuCode: Int): Fragment? {
    return when (menuCode) {
      NavigationConstants.NAVIGATE_TO_DEMO->DemoFragment()
      else -> null
    }
  }


  @JvmStatic
  fun isHandled(@NavigationConstants pageId: Int): Boolean {
    return when (pageId) {
      NavigationConstants.NAVIGATE_TO_DEMO -> true
      else -> return false
    }
  }
}