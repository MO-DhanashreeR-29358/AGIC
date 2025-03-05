package com.mosl.app.coreui.button

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

/**
 * Created by lalkishore on 7/13/2016.
 */
class CriticalButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatButton(context, attrs, defStyle) {

  /*
   * (non-Javadoc)
   * @see android.view.View#performClick()
   * Overridden to disable button on click permanently
   */
  override fun performClick(): Boolean {
    isEnabled = false
    return super.performClick()
  }
}
