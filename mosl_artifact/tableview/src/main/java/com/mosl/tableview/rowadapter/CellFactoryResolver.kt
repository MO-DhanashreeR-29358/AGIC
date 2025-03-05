package com.mosl.tableview.rowadapter

import androidx.annotation.MainThread

/**
 * This wraps header and body factories and provide convenience method to find CellFactory for a
 * given set of row & column. It optimizes for use case where header & body factories may contain
 * only one factory each. It also optimizes resolve(row, column) to return last accessed factory with
 * negligible cost.
 */
internal class CellFactoryResolver(
    protected val headerFactories: List<CellFactory>,
    protected val bodyFactories: List<CellFactory>
) {
  private val singleHeaderFactory = headerFactories.size == 1
  private val firstHeaderFactory = headerFactories.first()
  private val singleBodyFactory = bodyFactories.size == 1
  private val firstBodyFactory = bodyFactories.first()

  private var lastRow: Int = -100
  private var lastColumn: Int = -100
  private var lastCellFactory: CellFactory? = null

  init {
    require(headerFactories.isNotEmpty() && bodyFactories.isNotEmpty()) {
      "headerFactories & bodyFactories must have one Factory each"
    }
  }

  @MainThread
  internal fun resolve(row: Int, column: Int): CellFactory {
    val resolvedFactory = if (row == lastRow && column == lastColumn) {
      lastCellFactory
    } else if (row == -1) {
      if (singleHeaderFactory) {
        if (firstHeaderFactory.factoryFor(row, column)) {
          firstHeaderFactory
        } else {
          null
        }
      } else {
        headerFactories.firstOrNull { it.factoryFor(row, column) }
      }
    } else {
      if (singleBodyFactory) {
        if (firstBodyFactory.factoryFor(row, column)) {
          firstBodyFactory
        } else {
          null
        }
      } else {
        bodyFactories.firstOrNull { it.factoryFor(row, column) }
      }
    }

    lastRow = row
    lastColumn = column
    lastCellFactory = resolvedFactory

    return resolvedFactory ?: factoryNotInstalled(row, column)
  }

  internal val size: Int
    get() {
      return headerFactories.size + bodyFactories.size
    }

  private fun factoryNotInstalled(row: Int, column: Int): Nothing {
    throw error("factory not installed for row : $row, column : $column")
  }
}