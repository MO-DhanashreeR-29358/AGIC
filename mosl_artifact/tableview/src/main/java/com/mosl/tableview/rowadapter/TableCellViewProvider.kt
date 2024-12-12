package com.mosl.tableview.rowadapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.mosl.tableview.R
import com.mosl.tableview.rowadapter.SortOrder.ASCENDING

abstract class TableCellViewProvider(
    protected val inflater: LayoutInflater,
    protected val cellFactoryProvider: CellFactoryProvider
) {
  private val cellClickListener: OnClickListener = OnClickListener {
    onCellClicked(
        it.getTag(R.id.tag_row) as Int,
        it.getTag(R.id.tag_column) as Int,
        it.getTag(R.id.tag_type_view) as Int,
        it
    )
  }
  var rowClickListener: ((Int, View) -> Unit)? = null

  abstract val sortingEnabled: Boolean

  internal fun getView(row: Int, column: Int, convertView: View?, parent: ViewGroup): View {
    return cellFactoryProvider.getView(inflater, row, column, convertView, parent)
        .apply {
          setOnClickListener(cellClickListener)
        }
  }

  internal fun getItemViewType(row: Int, column: Int): Int {
    return cellFactoryProvider.getItemViewType(row, column)
  }

  internal fun onCellClicked(row: Int, column: Int, viewType: Int, view: View) {
    if (row == -1) {
      onHeaderClicked(column + 1)
    } else {
      onRowClicked(row, viewType, view)
    }
  }

  protected abstract fun onHeaderClicked(column: Int)

  protected open fun onRowClicked(row: Int, viewType: Int, view: View) {
    rowClickListener?.invoke(row, view)
  }

  internal fun getViewTypeCount(): Int {
    return cellFactoryProvider.size
  }

  fun headerHeight(): Int = cellFactoryProvider.headerHeight

  open fun rowHeight(): Int = cellFactoryProvider.rowHeight

  open val sortInfo: Pair<Int, SortOrder>
    get() = -1 to ASCENDING
}