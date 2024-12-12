package com.mosl.tableview.rowadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mosl.tableview.R

class CellFactoryProvider(
    context: Context,
    headerFactories: List<CellFactory>,
    bodyFactories: List<CellFactory>,
    @JvmField val headerHeight: Int = context.resources.getDimensionPixelSize(R.dimen.table_layout_header_height),
    @JvmField val rowHeight: Int = context.resources.getDimensionPixelSize(R.dimen.table_layout_height)
) {
  private val cellFactoryResolver = CellFactoryResolver(headerFactories, bodyFactories)

  constructor(context: Context,
      headerFactory: CellFactory,
      bodyFactory: CellFactory)
      : this(context, listOf(headerFactory), listOf(bodyFactory))

  constructor(context: Context,
      headerFactory: CellFactory,
      bodyFactories: List<CellFactory>)
      : this(context, listOf(headerFactory), bodyFactories)

  val size: Int = cellFactoryResolver.size

  fun getView(
      inflater: LayoutInflater,
      row: Int,
      column: Int,
      convertView: View?,
      parent: ViewGroup?
  ): View {
    return getCellFactory(row, column)
        .getView(inflater, row, column, convertView, parent)
  }

  fun getItemViewType(row: Int, column: Int): Int {
    return getCellFactory(row, column).getLayout()
  }

  @Suppress("NOTHING_TO_INLINE")
  internal inline fun getCellFactory(row: Int, column: Int): CellFactory {
    return cellFactoryResolver.resolve(row, column)
  }
}