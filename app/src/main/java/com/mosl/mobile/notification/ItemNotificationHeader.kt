package com.mosl.mobile.notification

import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentOverviewChildBinding
import com.mosl.mobile.databinding.ItemNotificationHeaderBinding
import com.xwray.groupie.databinding.BindableItem

class ItemNotificationHeader(

) :
    BindableItem<ItemNotificationHeaderBinding>() {


    override fun bind(viewBinding: ItemNotificationHeaderBinding, position: Int) {

        viewBinding.apply {

        }
    }

    override fun getLayout(): Int {
        return R.layout.item_notification_header
    }


}
