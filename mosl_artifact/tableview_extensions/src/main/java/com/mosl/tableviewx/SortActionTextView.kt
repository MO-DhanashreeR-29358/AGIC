package com.mosl.tableviewx

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created by lalkishore on 7/8/2016.
 */
class SortActionTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
  private var mIsSortedAsc = false

  fun setSorted(sorted: Boolean, asc: Boolean) {
    val icon = if (sorted) {
      if (asc) {
        R.drawable.ic_sorted_ascending_24dp
      } else {
        R.drawable.ic_sorted_descending_24dp
      }
    } else {
      R.drawable.ic_unsorted_24dp
    }
    setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0)
  }

  fun setSortedAsc(mIsSortedAsc: Boolean) {
    this.mIsSortedAsc = mIsSortedAsc
    refreshDrawableState()
  }

  override fun onCreateDrawableState(extraSpace: Int): IntArray {
    val drawableState = super.onCreateDrawableState(extraSpace + 2)
    if (mIsSortedAsc) {
      View.mergeDrawableStates(drawableState, STATE_SORTED_ASC)
    }
    return drawableState
  }

  companion object {
    private val STATE_SORTED_ASC = intArrayOf(R.attr.state_sorted_ascending)
  }
}
