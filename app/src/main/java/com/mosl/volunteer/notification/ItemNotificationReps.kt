package com.mosl.volunteer.notification

import com.mosl.volunteer.R
import com.mosl.volunteer.databinding.ItemNotificationRepsBinding
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