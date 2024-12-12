package com.mosl.mobile.overview

import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentOverviewChildBinding
import com.mosl.mobile.databinding.ItemRmDataChildBinding
import com.mosl.mobile.domain.overviewpager.ClientWiseMeetingSlotItemInfo
import com.mosl.mobile.domain.overviewpager.OverviewResponseDayItemInfo
import com.mosl.shared.util.getTimeAMPM
import com.mosl.groupiex.helper.databind.BindableItem
import io.reactivex.subjects.PublishSubject

class ItemClientWiseMetting(
    overviewData: ClientWiseMeetingSlotItemInfo,
) :
    BindableItem<ItemRmDataChildBinding>(){
    private val itemData = overviewData


    override fun bind(viewBinding: ItemRmDataChildBinding, position: Int) {

        viewBinding.apply {

            tvFundName.text = itemData.meetingSlotTime
            tvRoomNumber.text = itemData.meeting[0].roomNo


            iconRmGroup.apply {
                when (itemData.meeting[0].natureOfMeeting) {
                    "1-1" -> setImageResource(R.drawable.ic_1x1_small)
                    "2-1" -> setImageResource(R.drawable.ic_2x1_small)
                    "3-1" -> setImageResource(R.drawable.ic_3x1_small)
                    "4-1" -> setImageResource(R.drawable.ic_4x1_small)
                    "Small Group" -> setImageResource(R.drawable.ic_group_small)
                    "Workshop" -> setImageResource(R.drawable.ic_mtrack_small)
                    else -> setImageResource(R.drawable.ic_mtrack_small)
                }
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_rm_data_child
    }


}
