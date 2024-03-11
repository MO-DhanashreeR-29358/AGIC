package com.mosl.mobile.volunteer.corporateschedule

import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.databinding.ItemFragmentScheduleRepoBinding
import com.mosl.mobile.volunteer.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo
import com.xwray.groupie.databinding.BindableItem


class ItemCorporateScheduleChildRepo(
    private val scheduleData: CorporateScheduleResponseRepresentativeItemInfo
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