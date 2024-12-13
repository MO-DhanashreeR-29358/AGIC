package com.mosl.tableviewx

import android.content.Context
import android.view.LayoutInflater
import com.mosl.tableview.rowadapter.CellFactoryProvider
import com.mosl.tableview.rowadapter.SortOrder
import com.mosl.tableview.rowadapter.TableCellViewProvider
import com.mosl.tableview.rowadapter.TableDataSet

open class SortedTableViewProvider(
    context: Context,
    tableDataSet: TableDataSet<*>,
    @JvmField val sortHeaderCellFactory: SortHeaderCellFactory = SortHeaderCellFactory(tableDataSet.header),
    cellFactoryProvider: CellFactoryProvider = CellFactoryProvider(
        context,
        sortHeaderCellFactory,
        listOf(
            FirstBodyCellFactory(tableDataSet),
            SimpleBodyCellFactory(tableDataSet)
        )
    )
) : TableCellViewProvider(LayoutInflater.from(context), cellFactoryProvider) {
  private var sortClickListener: ((Pair<Int, SortOrder>) -> Unit)? = null

  fun enableSorting(defaultColumn: Int = 0, sortListener: (Pair<Int, SortOrder>) -> Unit) {
    this.sortClickListener = sortListener
    sortHeaderCellFactory.setSortInfo(defaultColumn)
  }

  fun disableSorting() {
    this.sortClickListener = null
    sortHeaderCellFactory.setSortInfo(-1)
  }

  override val sortingEnabled: Boolean
    get() {
      return sortClickListener != null
    }

  override fun onHeaderClicked(column: Int) {
    sortClickListener?.let {
      sortHeaderCellFactory.setSortInfo(column)
      it.invoke(sortInfo)
    }
  }

  override val sortInfo: Pair<Int, SortOrder>
    get() = sortHeaderCellFactory.sortInfo
}