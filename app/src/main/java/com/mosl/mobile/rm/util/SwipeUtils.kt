@file:JvmName("SwipeUtils")
package com.mosl.mobile.rm.util

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mosl.groupiex.section.DataManagerSection

fun SwipeRefreshLayout.toggleProgressBar(
  section: DataManagerSection,
  inProgress: Boolean
) {
  if (inProgress) {
    section.clearErrorState()
  }
  isRefreshing = inProgress
}