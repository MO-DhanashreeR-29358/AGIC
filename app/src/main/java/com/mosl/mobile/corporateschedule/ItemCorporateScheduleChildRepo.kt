package com.mosl.mobile.corporateschedule

import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentScheduleRepoBinding
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseRepresentativeItemInfo
import com.mosl.groupiex.helper.databind.BindableItem


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