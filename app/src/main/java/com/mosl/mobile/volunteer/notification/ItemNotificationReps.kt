package com.mosl.mobile.volunteer.notification

import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.databinding.ItemNotificationRepsBinding
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