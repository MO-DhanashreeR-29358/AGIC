package com.mosl.volunteer.nav

import androidx.fragment.app.Fragment
import com.mosl.volunteer.menu.MenuGetter
import com.mosl.navigator.FragmentResolver

object DebugFragmentResolver : FragmentResolver {
  override fun resolve(pageId: Int): Fragment? {
    return when {
      DebugMenuGetter.isHandled(pageId) -> DebugMenuGetter.getFragment(pageId)
      else -> MenuGetter.getFragment(pageId)
    }
  }
}