package com.mosl.mobile.overview


import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentOverviewChildBinding
import com.mosl.mobile.domain.overviewpager.OverviewPagerResponseDayItemInfo
import com.mosl.shared.util.getTimeAMPM
import com.xwray.groupie.databinding.BindableItem


class ItemOverview(overviewData: OverviewPagerResponseDayItemInfo) :
    BindableItem<ItemFragmentOverviewChildBinding>() {
    private val itemData = overviewData


    override fun bind(viewBinding: ItemFragmentOverviewChildBinding, position: Int) {

        viewBinding.tvOverviewDesignation.text = itemData.corporate_Name
        viewBinding.tvOverviewName.text = itemData.Rep[0].NAME
        viewBinding.overviewTvTimeFrom.text = getTimeAMPM(itemData.start_time)
        viewBinding.tvOverviewTimeTo.text = getTimeAMPM(itemData.end_time)
    }

    override fun getLayout(): Int {
        return R.layout.item_fragment_overview_child
    }
}
