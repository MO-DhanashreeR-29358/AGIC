package com.mosl.mobile.menu

import androidx.fragment.app.Fragment
import com.mosl.navigator.FragmentResolver

object AppFragmentResolver : FragmentResolver {
  override fun resolve(pageId: Int): Fragment? {
    return when {
      else -> MenuGetter.getFragment(pageId)
    }
  }
}