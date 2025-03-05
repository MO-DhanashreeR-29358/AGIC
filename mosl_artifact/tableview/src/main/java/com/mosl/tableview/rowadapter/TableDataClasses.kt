package com.mosl.tableview.rowadapter

import androidx.annotation.StringRes

interface TableRow {
  fun get(column: Int): String
}

data class Header(
    @JvmField val label: String?,
    @JvmField @StringRes val labelRes: Int = -1
) {
  companion object {
    @JvmField
    val EmptyHeader = Header("")
  }
}

abstract class TableDataSet<T : TableRow>(
    @JvmField val header: HeaderRow,
    protected val dataList: MutableList<T>
) {

  init {
    require(header.size > 0) {
      "header cannot be empty"
    }
  }

  fun get(position: Int): T {
    return dataList[position]
  }

  fun getAll(): List<T> {
    return dataList.toList()
  }

  fun set(position: Int, row: T) {
    dataList[position] = row
  }

  fun addAll(rows: List<T>) {
    dataList.addAll(rows)
  }

  fun add(row: T) {
    dataList.add(row)
  }

  fun clear() {
    dataList.clear()
  }

  fun columnCount(): Int {
    return header.size
  }

  fun rowCount(): Int {
    return dataList.size
  }
}

class SimpleTableDataSet<T : TableRow> @JvmOverloads constructor(
    header: HeaderRow,
    dataList: MutableList<T> = mutableListOf()
) : TableDataSet<T>(header, dataList)