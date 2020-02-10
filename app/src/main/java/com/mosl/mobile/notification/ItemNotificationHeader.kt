package com.mosl.mobile.notification

import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentOverviewChildBinding
import com.xwray.groupie.databinding.BindableItem

class ItemNotificationHeader(

) :
    BindableItem<ItemFragmentOverviewChildBinding>() {


    override fun bind(viewBinding: ItemFragmentOverviewChildBinding, position: Int) {

        viewBinding.apply {

        }
    }

    override fun getLayout(): Int {
        return R.layout.item_fragment_overview_child
    }


}
