package com.mosl.tableview.rowadapter

enum class SortOrder {
  ASCENDING,
  DESCENDING;

  val isAscending: Boolean
    get() {
      return this == ASCENDING
    }

  fun toggle(): SortOrder {
    return if (isAscending) SortOrder.DESCENDING else SortOrder.ASCENDING
  }
}

fun <T : TableRow> List<T>.sortTableData(
    columnId: Int,
    sortOrder: SortOrder
): List<T> {
  if (size == 0) {
    return this
  }
  val tempData = toList()

  val sortedList = if (tempData[0].get(columnId).toDoubleOrNull() != null) {
    tempData.sortedBy {
      it.get(columnId).toDoubleOrZero()
    }
  } else {
    tempData.sortedBy {
      it.get(columnId)
    }
  }

  return if (sortOrder == SortOrder.DESCENDING) {
    sortedList.reversed()
  } else {
    sortedList
  }
}

fun String?.toDoubleOrZero(): Double {
  return this?.toDoubleOrNull() ?: 0.0
}

fun <T : TableRow> TableRowAdapter<T>.sortDataAndSet(rows: List<T>) {
  sortDataAndSet(rows, viewProvider.sortInfo)
}

fun <T : TableRow> TableRowAdapter<T>.sortDataAndSet(rows: List<T>, pair: Pair<Int, SortOrder>) {
  val (columnId, sortOrder) = pair
  setData(rows.sortTableData(columnId, sortOrder))
}