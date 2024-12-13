package com.mosl.tableview.rowadapter

import android.content.Context

interface HeaderRow {
  fun width(column: Int): Int
  fun get(column: Int): Header
  val size: Int
}

class SimpleHeaderRow(
    context: Context,
    widths: IntArray,
    protected val headers: List<Header>
) : HeaderRow {
  protected val columnWidths: IntArray

  constructor(context: Context, widths: IntArray, headers: IntArray)
      : this(context, widths, headers.toTableHeader())

  constructor(context: Context, widths: IntArray, headers: Array<String>)
      : this(context, widths, headers.toTableHeader())

  init {
    require(headers.isNotEmpty() && headers.size == widths.size) {
      "headers and layout Width must be of same positive size."
    }

    val density = context.resources.displayMetrics.density
    columnWidths = widths.map {
      Math.round(it * density)
    }.toIntArray()
  }

  override fun width(column: Int): Int {
    return if (column < columnWidths.size - 1) {
      columnWidths[column + 1]
    } else {
      0
    }
  }

  override fun get(column: Int): Header {
    return headers.getOrNull(column) ?: Header.EmptyHeader
  }

  override val size: Int
    get() = headers.size
}

fun Array<String>.toTableHeader(): List<Header> {
  return map { Header(label = it) }
}

fun IntArray.toTableHeader(): List<Header> {
  return map { Header(label = null, labelRes = it) }
}