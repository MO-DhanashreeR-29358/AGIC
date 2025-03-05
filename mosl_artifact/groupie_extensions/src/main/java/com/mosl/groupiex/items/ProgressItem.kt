package com.mosl.groupiex.items

import com.mosl.groupiex.R
import com.mosl.groupiex.helper.groupie.GroupieViewHolder
import com.mosl.groupiex.helper.groupie.Item

class ProgressItem : Item<GroupieViewHolder>() {

  override fun getLayout(): Int {
    return R.layout.item_progress_bar
  }

  override fun bind(viewHolder: GroupieViewHolder, position: Int) {}

  override fun isClickable(): Boolean {
    return false
  }
}
