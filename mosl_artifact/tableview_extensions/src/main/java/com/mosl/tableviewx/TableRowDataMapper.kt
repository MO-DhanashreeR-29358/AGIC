package com.mosl.tableviewx

import android.content.Context
import com.mosl.tableview.rowadapter.HeaderRow
import com.mosl.tableview.rowadapter.TableRow

interface TableRowDataMapper<T, R : TableRow> {
  fun header(context: Context): HeaderRow
  fun map(input: List<T>): List<R>
}