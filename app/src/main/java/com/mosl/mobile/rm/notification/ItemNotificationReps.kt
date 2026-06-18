package com.mosl.mobile.rm.notification

import com.mosl.mobile.rm.R
import com.mosl.mobile.rm.databinding.ItemNotificationRepsBinding
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