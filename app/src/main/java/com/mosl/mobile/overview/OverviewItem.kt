package com.mosl.mobile.overview

import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentOverviewBinding
import com.mosl.mobile.databinding.ItemFragmentPanelBinding
import com.mosl.mobile.panel.PanelDataHeader
import com.xwray.groupie.databinding.BindableItem


class OverviewItem(overviewData: OverviewData) : BindableItem<ItemFragmentOverviewBinding>() {
    private val itemData = overviewData


    override fun bind(viewBinding: ItemFragmentOverviewBinding, position: Int) {

        viewBinding.txtName.text = itemData.name
        viewBinding.txtNamePost.text = itemData.postName
        viewBinding.txtFrom.text = itemData.timeFrom
        viewBinding.txtToEnd.text = itemData.timeTo


    }

    override fun getLayout(): Int {
        return R.layout.item_fragment_overview
    }
}
