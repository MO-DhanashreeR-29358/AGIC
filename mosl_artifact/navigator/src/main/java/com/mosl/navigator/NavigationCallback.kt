package com.mosl.navigator

import androidx.fragment.app.Fragment

interface NavigationCallback {
  fun onPageOpened(fragment: Fragment, addedToStack: Boolean)

  fun onBackNavigation()
}