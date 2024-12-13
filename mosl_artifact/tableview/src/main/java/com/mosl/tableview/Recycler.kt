package com.mosl.tableview

import android.util.SparseArray
import android.view.View
import java.util.Stack

/**
 * The Recycler facilitates reuse of views across layouts.
 *
 * Created by feat systems
 */
class Recycler
/**
 * Constructor
 *
 * @param size The number of types of view to recycle.
 */
(size: Int) {
  private val views: SparseArray<Stack<View>> = SparseArray(size)

  /**
   * Add a view to the Recycler. This view may be reused in the function
   * [.getRecycledView]
   *
   * @param view A view to add to the Recycler. It can no longer be used.
   * @param type the type of the view.
   */
  fun addRecycledView(view: View, type: Int) {
    this.views.get(type).let { stack ->
      val tempStack = stack ?: Stack<View>().also {
        this.views.put(type, it)
      }
      tempStack.push(view)
    }
  }

  /**
   * Returns, if exists, a view of the type `typeView`.
   *
   * @param typeView the type of view that you want.
   * @return a view of the type `typeView`. `null` if
   * not found.
   */
  fun getRecycledView(typeView: Int): View? {
    return try {
      views.get(typeView)?.pop()
    } catch (e: java.util.EmptyStackException) {
      null
    }
  }
}
