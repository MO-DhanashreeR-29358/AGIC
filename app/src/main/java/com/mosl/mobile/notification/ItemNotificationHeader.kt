package com.mosl.mobile.notification

import com.mosl.mobile.R
import com.mosl.mobile.core.MOSLCommonActivity
import com.mosl.mobile.databinding.ItemNotificationHeaderBinding
import com.xwray.groupie.databinding.BindableItem

class ItemNotificationHeader(
    val role: String
) :
    BindableItem<ItemNotificationHeaderBinding>() {


    override fun bind(viewBinding: ItemNotificationHeaderBinding, position: Int) {

        viewBinding.apply {
            if (role.equals(MOSLCommonActivity.ROLE_CLIENT, ignoreCase = true)) {
                tvHeaderCorporate.setText(CORPORATE)
            } else {
                tvHeaderCorporate.setText(FUND)
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_notification_header
    }

    companion object {
        const val CORPORATE = "Corporate"
        const val FUND = "Fund"
    }
}
