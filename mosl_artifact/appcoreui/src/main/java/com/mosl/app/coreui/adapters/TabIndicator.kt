package com.mosl.app.coreui.adapters

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface TabIndicator {
  val label: Int
  val icon: Int
}

data class SimpleTabIndicator(
    @StringRes override val label: Int,
    @DrawableRes override val icon: Int
) : TabIndicator