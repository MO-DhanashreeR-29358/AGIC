package com.mosl.mobile.rm.menu

import androidx.fragment.app.Fragment
import com.mosl.navigator.FragmentResolver

object AppFragmentResolver : FragmentResolver {
  override fun resolve(pageId: Int): Fragment? {
    return when {
      MenuGetter.isHandled(pageId) -> MenuGetter.getFragment(pageId)
      else -> null
    }
  }
}