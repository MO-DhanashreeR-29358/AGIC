package com.mosl.mobile.volunteer.helper

import com.mosl.navigator.NavigationEntry

data class CustomNavigationEntry(
  @JvmField val pageId: Int,
  @JvmField val data: Any? = null
) : NavigationEntry()