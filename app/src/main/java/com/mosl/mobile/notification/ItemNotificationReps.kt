package com.mosl.mobile.notification

import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemNotificationRepsBinding
import com.xwray.groupie.databinding.BindableItem


class ItemNotificationReps(
     val repsName: String
) :
    BindableItem<ItemNotificationRepsBinding>() {

    override fun getLayout(): Int {
        return R.layout.item_notification_reps
    }

    override fun bind(viewBinding: ItemNotificationRepsBinding, position: Int) {

        viewBinding.tvRepsName.text = repsName
    }
}