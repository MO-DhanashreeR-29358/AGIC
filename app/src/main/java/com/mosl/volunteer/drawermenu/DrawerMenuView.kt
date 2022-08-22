package com.mosl.volunteer.drawermenu

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.mosl.app.core.utils.obtain
import com.mosl.volunteer.ApplicationComponent
import com.mosl.volunteer.R

class DrawerMenuView(
  context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
  init {
    context.applicationContext.obtain(ApplicationComponent::class.java).inject(this)
    LayoutInflater.from(context).inflate(R.layout.view_drawer_menu, this)
  }
}