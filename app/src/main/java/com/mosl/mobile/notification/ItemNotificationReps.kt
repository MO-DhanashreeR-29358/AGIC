package com.mosl.mobile.notification

import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemNotificationRepsBinding
import com.mosl.groupiex.helper.databind.BindableItem


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