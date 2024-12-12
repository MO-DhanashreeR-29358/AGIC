package com.mosl.app.coreui.button

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

/**
 * Created by lalkishore on 7/13/2016.
 */
class DelayButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatButton(context, attrs, defStyle) {
  private val buttonEnabler = { isEnabled = true }
  private var clickDelay = 1000L
  private var isClickDelayEnabled = false

  fun setClickDelay(clickDelay: Int) {
    setClickDelayEnabled(true)
    this.clickDelay = clickDelay.toLong()
  }

  private fun setClickDelayEnabled(isClickDelayEnabled: Boolean) {
    this.isClickDelayEnabled = isClickDelayEnabled
  }

  /*
   * (non-Javadoc)
   * @see android.view.View#performClick()
   * Overridden to handle click delay manually
   */
  override fun performClick(): Boolean {
    if (isClickDelayEnabled) {
      isEnabled = false
      postDelayed(buttonEnabler, clickDelay)
    }
    return super.performClick()
  }
}
