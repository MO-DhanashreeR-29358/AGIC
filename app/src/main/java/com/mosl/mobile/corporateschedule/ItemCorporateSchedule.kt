package com.mosl.mobile.corporateschedule

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentCorporateScheduleChildBinding
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.mosl.shared.util.getTimeAMPM
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.subjects.PublishSubject

class ItemCorporateSchedule(
    scheduleData: CorporateScheduleResponseDayItemInfo,
    private val publishSubject: PublishSubject<CorporateScheduleUiEvent>,
    private val currentPosition: Int
) :
    BindableItem<ItemFragmentCorporateScheduleChildBinding>() {
    private val itemData = scheduleData


    override fun bind(viewBinding: ItemFragmentCorporateScheduleChildBinding, position: Int) {

        viewBinding.apply {

            tvShiftTime.text = getTimeAMPM(itemData.shiftTiming)
            tvRoomNumber.text = String.format("Room No - %s", itemData.roomNumber)

            img.apply {
                when (itemData.natureOfMeeting) {
                    "1-1" -> setImageResource(R.drawable.ic_1x1_small)
                    "2-1" -> setImageResource(R.drawable.ic_2x1_small)
                    "3-1" -> setImageResource(R.drawable.ic_3x1_small)
                    "4-1" -> setImageResource(R.drawable.ic_4x1_small)
                    "Small Group" -> setImageResource(R.drawable.ic_group_small)
                    "Workshop" -> setImageResource(R.drawable.ic_mtrack_small)
                    else -> setImageResource(R.drawable.ic_mtrack_small)
                }
            }

            if (currentPosition == position) {
                rabView.setBackgroundResource(R.drawable.bg_blue_circle)
                recAttendees.visibility = View.VISIBLE
            } else {
                rabView.setBackgroundResource(R.drawable.bg_grey_circle)
                recAttendees.visibility = View.GONE
            }

            setupRecyclerView(viewBinding)
        }
    }

    @JvmField
    protected val onRetryClick = View.OnClickListener {

    }

    private fun setupRecyclerView(binding: ItemFragmentCorporateScheduleChildBinding) {


        binding.tvShiftTime.setOnClickListener(View.OnClickListener {
            if (binding.recAttendees.visibility == View.VISIBLE) {
                binding.recAttendees.visibility = View.GONE
                binding.rabView.setBackgroundResource(R.drawable.bg_grey_circle)
            } else {
                binding.recAttendees.visibility = View.VISIBLE
                binding.rabView.setBackgroundResource(R.drawable.bg_blue_circle)
            }

        })

        binding.tvRoomNumber.setOnClickListener(View.OnClickListener {
            if (binding.recAttendees.visibility == View.VISIBLE) {
                binding.recAttendees.visibility = View.GONE
                binding.rabView.setBackgroundResource(R.drawable.bg_grey_circle)
            } else {
                binding.recAttendees.visibility = View.VISIBLE
                binding.rabView.setBackgroundResource(R.drawable.bg_blue_circle)
            }

        })

        val groupAdapter = GroupAdapter<ViewHolder>()

        val section = DataManagerSection(onRetryClick)
        groupAdapter.add(section)

        binding.recAttendees.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            adapter = groupAdapter

            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        for (model in itemData.Attendees) {
            section.add(ItemCorporateScheduleChild(model, publishSubject))
        }
    }


    override fun getLayout(): Int {
        return R.layout.item_fragment_corporate_schedule_child
    }

}
