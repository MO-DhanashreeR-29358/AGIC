package com.mosl.tableview.rowadapter

import android.view.View
import android.view.ViewGroup
import com.mosl.tableview.adapters.BaseTableAdapter

class TableRowAdapter<T : TableRow>(
    protected val tableDataSet: TableDataSet<T>,
    @JvmField val viewProvider: TableCellViewProvider
) : BaseTableAdapter() {
  protected val header = tableDataSet.header

  fun setData(rows: List<T>) {
    tableDataSet.clear()
    addAll(rows)
  }

  fun addAll(rows: List<T>) {
    tableDataSet.addAll(rows)
    notifyDataSetChanged()
  }

  fun add(row: T) {
    tableDataSet.add(row)
  }

  fun clear() {
    tableDataSet.clear()
    notifyDataSetChanged()
  }

  fun size(): Int {
    return rowCount
  }

  override fun getRowCount(): Int {
    return tableDataSet.rowCount()
  }

  override fun getColumnCount(): Int {
    return tableDataSet.columnCount()
  }

  override fun getHeight(row: Int): Int {
    return if (row == -1) {
      viewProvider.headerHeight()
    } else {
      viewProvider.rowHeight()
    }
  }

  override fun getWidth(column: Int): Int {
    return header.width(column)
  }

  override fun getView(row: Int, column: Int, convertView: View?, parent: ViewGroup): View {
    return viewProvider.getView(row, column, convertView, parent)
  }

  override fun getItemViewType(row: Int, column: Int): Int {
    return viewProvider.getItemViewType(row, column)
  }

  override fun getViewTypeCount(): Int {
    return viewProvider.getViewTypeCount()
  }

  fun getItem(position: Int): T {
    return tableDataSet.get(position)
  }

  fun setItem(position: Int, row: T) {
    tableDataSet.set(position, row)
  }
}