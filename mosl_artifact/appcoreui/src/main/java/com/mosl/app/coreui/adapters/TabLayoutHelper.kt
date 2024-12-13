@file:JvmName("TabLayoutHelper")

package com.mosl.app.coreui.adapters

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.mosl.app.coreui.R

fun TabLayout.populateTabWithIcons(tabs: List<TabIndicator>) {
  fun createTabView(tabIndicator: TabIndicator): View {
    return (LayoutInflater.from(context).inflate(R.layout.view_custom_tab, null) as TextView).apply {
      setText(tabIndicator.label)
      setCompoundDrawablesWithIntrinsicBounds(0, tabIndicator.icon, 0, 0)
    }
  }

  for (position in 0 until tabCount) {
    getTabAt(position)?.customView = createTabView(tabs[position])
  }
}