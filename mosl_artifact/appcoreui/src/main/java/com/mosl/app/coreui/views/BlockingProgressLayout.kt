package com.mosl.app.coreui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.mosl.app.coreui.R

/**
 * Created by lalkishore on 04/02/18.
 */

class BlockingProgressLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
  init {
    isFocusable = true
    isClickable = true
    LayoutInflater.from(context).inflate(R.layout.view_progressbar, this, true)
  }
}
