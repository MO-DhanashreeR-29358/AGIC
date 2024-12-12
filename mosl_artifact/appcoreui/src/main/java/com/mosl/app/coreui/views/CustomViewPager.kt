package com.mosl.app.coreui.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by lalkishore on 9/9/2015.
 */
class CustomViewPager(
    context: Context,
    attrs: AttributeSet?
) : ViewPager(context, attrs) {
  private var pagingEnabled: Boolean = true

  fun setPagingEnabled(enabled: Boolean) {
    this.pagingEnabled = enabled
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    return this.pagingEnabled && super.onTouchEvent(event)
  }

  override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
    return this.pagingEnabled && super.onInterceptTouchEvent(event)
  }
}
