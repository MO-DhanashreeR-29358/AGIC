package com.mosl.volunteer.schedule

import com.mosl.volunteer.R
import com.mosl.volunteer.databinding.ItemFragmentScheduleRepoBinding
import com.mosl.volunteer.domain.schedule.ScheduleRepresentativeItemData
import com.xwray.groupie.databinding.BindableItem


class ItemScheduleRepo(
    private val scheduleData: ScheduleRepresentativeItemData
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