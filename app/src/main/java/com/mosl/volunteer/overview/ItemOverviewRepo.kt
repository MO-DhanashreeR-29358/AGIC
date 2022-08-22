package com.mosl.volunteer.overview

import com.mosl.volunteer.R
import com.mosl.volunteer.databinding.ItemFragmentScheduleRepoBinding
import com.mosl.volunteer.domain.overviewpager.OverviewResponseRepresntativeInfo
import com.xwray.groupie.databinding.BindableItem


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