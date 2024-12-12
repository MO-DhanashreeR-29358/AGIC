package com.mosl.tableview.rowadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class CellFactory {

  abstract fun factoryFor(row: Int, column: Int): Boolean

  @LayoutRes
  abstract fun getLayout(): Int

  fun getView(
      inflater: LayoutInflater,
      row: Int,
      column: Int,
      convertView: View?,
      parent: ViewGroup?
  ): View {
    val view = convertView
        ?: inflater.inflate(getLayout(), parent, false)
    bindView(view, row, column)
    return view
  }

  protected open fun bindView(view: View, row: Int, column: Int) {}
}