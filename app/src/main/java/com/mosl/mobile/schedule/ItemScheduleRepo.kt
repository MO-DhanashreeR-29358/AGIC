package com.mosl.mobile.schedule

import android.content.Context
import com.mosl.agicapp.R
import com.mosl.agicapp.databinding.ItemFragmentScheduleRepoBinding
import com.mosl.mobile.domain.schedule.ScheduleRepresentativeItemData
import com.mosl.groupiex.helper.databind.BindableItem


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