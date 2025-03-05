package com.mosl.tableviewx

import android.view.Gravity
import android.view.View
import com.mosl.tableview.rowadapter.CellFactory
import com.mosl.tableview.rowadapter.HeaderRow
import com.mosl.tableview.rowadapter.SortOrder

abstract class HeaderCellFactory(
    @JvmField protected val header: HeaderRow
) : CellFactory() {

  final override fun factoryFor(row: Int, column: Int): Boolean {
    return row == -1
  }
}

open class SortHeaderCellFactory(
    headers: HeaderRow,
    private var sortByColumnId: Int = -1,
    private var sortOrder: SortOrder = SortOrder.ASCENDING
) : HeaderCellFactory(headers) {

  fun setSortInfo(sortByColumnId: Int) {
    if (this.sortByColumnId == sortByColumnId) {
      this.sortOrder = this.sortOrder.toggle()
    } else {
      this.sortByColumnId = sortByColumnId
    }
  }

  val sortInfo: Pair<Int, SortOrder>
    get() = sortByColumnId to sortOrder

  override fun getLayout(): Int {
    return R.layout.item_grid_header
  }

  override fun bindView(view: View, row: Int, column: Int) {
    val tv = view as SortActionTextView
    val normalizedColumn = column + 1
    header.get(normalizedColumn).let { (label, labelRes) ->
      if (label != null) {
        tv.text = label
      } else {
        tv.setText(labelRes)
      }
    }
    if (column == -1) {
      tv.gravity = Gravity.CENTER_VERTICAL or Gravity.START
    } else {
      tv.gravity = Gravity.CENTER
    }
    tv.setSorted(sortByColumnId == normalizedColumn, sortOrder.isAscending)
  }
}