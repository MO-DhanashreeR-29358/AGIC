package com.mosl.mobile.schedule

import android.content.Context
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentScheduleRepoBinding
import com.mosl.mobile.domain.schedule.ScheduleRepresentativeItemData
import com.xwray.groupie.databinding.BindableItem


class ItemScheduleRepo(
    private val scheduleData: ScheduleRepresentativeItemData
) :
    BindableItem<ItemFragmentScheduleRepoBinding>() {

    override fun getLayout(): Int {
        return R.layout.item_fragment_schedule_repo
    }

    override fun bind(viewBinding: ItemFragmentScheduleRepoBinding, position: Int) {

        viewBinding.tvScheduleRepoDesignation.text = scheduleData.DESIGNATION
        viewBinding.tvScheduleRepoName.text = scheduleData.NAME
    }
}