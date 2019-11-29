package com.mosl.mobile.overview


import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentOverviewBinding
import com.xwray.groupie.databinding.BindableItem


class OverviewItem(overviewData: OverviewData) : BindableItem<ItemFragmentOverviewBinding>() {
    private val itemData = overviewData


    override fun bind(viewBinding: ItemFragmentOverviewBinding, position: Int) {

        viewBinding.tvOverviewName.text = itemData.name
        viewBinding.tvOverviewDesignation.text = itemData.designation
        viewBinding.overviewTvTimeFrom.text = itemData.timeFrom
        viewBinding.tvOverviewTimeTo.text = itemData.timeTo


    }

    override fun getLayout(): Int {
        return R.layout.item_fragment_overview
    }
}
