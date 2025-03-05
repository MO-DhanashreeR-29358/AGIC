package com.mosl.tableviewx

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import com.mosl.tableview.rowadapter.CellFactory
import com.mosl.tableview.rowadapter.TableDataSet

abstract class BodyCellFactory(
    @JvmField protected val tableDataSet: TableDataSet<*>
) : CellFactory() {

  override fun factoryFor(row: Int, column: Int): Boolean {
    return row >= 0
  }

  fun getItem(row: Int, column: Int): String {
    return tableDataSet.get(row).get(column + 1)
  }

  @ColorRes
  fun getBackgroundColor(row: Int): Int {
    return if (row % 2 == 0)
      R.color.table_background_color_light
    else
      R.color.table_background_color
  }
}

open class FirstBodyCellFactory(
    tableDataSet: TableDataSet<*>
) : BodyCellFactory(tableDataSet) {

  final override fun factoryFor(row: Int, column: Int): Boolean {
    return super.factoryFor(row, column) && column == -1
  }

  override fun getLayout(): Int {
    return R.layout.item_grid_first_body
  }

  override fun bindView(view: View, row: Int, column: Int) {
    (view as TextView).apply {
      text = getItem(row, column)
      setBackgroundResource(
          if (row % 2 == 0) R.drawable.single_border_odd
          else R.drawable.single_border_even
      )
    }
  }
}

open class SimpleBodyCellFactory(
    tableDataSet: TableDataSet<*>
) : BodyCellFactory(tableDataSet) {

  final override fun factoryFor(row: Int, column: Int): Boolean {
    return super.factoryFor(row, column) && column >= 0
  }

  override fun getLayout(): Int {
    return R.layout.item_grid_body
  }

  override fun bindView(view: View, row: Int, column: Int) {
    (view as TextView).apply {
      text = getItem(row, column)
      setBackgroundResource(getBackgroundColor(row))
    }
  }
}