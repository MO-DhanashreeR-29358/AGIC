package com.mosl.mobile.menu

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.mosl.app.coreui.base.LeanBaseFragment
import com.mosl.app.coreui.base.PagerBaseFragment
import com.mosl.mobile.constants.NavigationConstants
import com.mosl.mobile.demo.DemoMVVMFragment

object MenuGetter {
  const val ARG_TARGET_ITEM_POSITION = PagerBaseFragment.ARG_TARGET_ITEM_POSITION
  const val PAGE_IS_SENSITIVE = "isSensitive"

  @SuppressLint("SwitchIntDef")
  fun getFragment(@NavigationConstants menuCode: Int): Fragment? {
    return when (menuCode) {
      NavigationConstants.NAVIGATE_TO_PORTFOLIO->DemoMVVMFragment()
      else -> null
    }
  }

  fun LeanBaseFragment.withIsSensitiveFlag(): LeanBaseFragment {
    arguments = bundleOf(PAGE_IS_SENSITIVE to true)
    return this
  }

  fun PagerBaseFragment.withPageSource(source: Int): PagerBaseFragment {
    arguments = bundleOf(PagerBaseFragment.ARG_TARGET_ITEM_POSITION to source)
    return this
  }

  fun isActivity(pageId: Int): Boolean {
    return false
  }

  fun isDialog(pageId: Int): Boolean {
    return false
  }

  fun isExternalWebLink(pageId: Int): Boolean {
    return false
  }

  fun isWebLink(pageId: Int): Boolean {
    return false
  }

  fun isCustomNavigation(pageId: Int): Boolean {
    return false
  }
}