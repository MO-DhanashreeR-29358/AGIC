package com.mosl.mobile.volunteer.overview

import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.databinding.ItemFragmentScheduleRepoBinding
import com.mosl.mobile.volunteer.domain.overviewpager.OverviewResponseRepresntativeInfo
import com.mosl.groupiex.helper.databind.BindableItem


class ItemOverviewRepo(
    private val scheduleData: OverviewResponseRepresntativeInfo
) :
    BindableItem<ItemFragmentScheduleRepoBinding>() {

    override fun getLayout(): Int {
        return R.layout.item_fragment_schedule_repo
    }

    override fun bind(viewBinding: ItemFragmentScheduleRepoBinding, position: Int) {

        viewBinding.tvScheduleRepoDesignation.text = scheduleData.designation
        viewBinding.tvScheduleRepoName.text = scheduleData.name
    }
}