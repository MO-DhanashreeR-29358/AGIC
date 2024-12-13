package com.mosl.tableviewx

import android.content.Context
import com.mosl.tableview.rowadapter.SimpleTableDataSet
import com.mosl.tableview.rowadapter.TableRow
import com.mosl.tableview.rowadapter.TableRowAdapter
import com.mosl.tableview.rowadapter.sortDataAndSet

fun <R : TableRow> Context.createAdapter(dataMapper: TableRowDataMapper<*, R>, enableSorting: Boolean = true): TableRowAdapter<R> {
  val dataSet = SimpleTableDataSet<R>(dataMapper.header(this))
  val tableViewProvider = SortedTableViewProvider(this, dataSet)
  val tableRowAdapter = TableRowAdapter(
      tableDataSet = dataSet,
      viewProvider = tableViewProvider
  )
  if (enableSorting) {
    tableViewProvider.enableSorting { pair ->
      tableRowAdapter.sortDataAndSet(dataSet.getAll(), pair)
    }
  }
  return tableRowAdapter
}