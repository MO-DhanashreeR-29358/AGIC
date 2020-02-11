package com.mosl.mobile.corporateschedule

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentCorporateScheduleChildBinding
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.subjects.PublishSubject

class ItemCorporateSchedule(
    scheduleData: CorporateScheduleResponseDayItemInfo,
    private val publishSubject: PublishSubject<CorporateScheduleUiEvent>
) :
    BindableItem<ItemFragmentCorporateScheduleChildBinding>() {
    private val itemData = scheduleData


    override fun bind(viewBinding: ItemFragmentCorporateScheduleChildBinding, position: Int) {

        viewBinding.apply {

            tvShiftTime.text = itemData.shiftTiming
            tvRoomNumber.text = "Room No - " + itemData.roomNumber

            setupRecyclerView(viewBinding)
        }
    }

//    @JvmField
//    protected val onRetryClick = View.OnClickListener {
//
//
//    }

    @JvmField
    protected val onRetryClick = View.OnClickListener {

    }

    private val followUpUiEvent = PublishSubject.create<CorporateScheduleUiEvent>()

    private fun setupRecyclerView(binding: ItemFragmentCorporateScheduleChildBinding) {


        binding.tvShiftTime.setOnClickListener(View.OnClickListener {
            if (binding.recAttendees.visibility == View.VISIBLE)
                binding.recAttendees.visibility = View.GONE
            else
                binding.recAttendees.visibility = View.VISIBLE
        })

        binding.tvRoomNumber.setOnClickListener(View.OnClickListener {
            if (binding.recAttendees.visibility == View.VISIBLE)
                binding.recAttendees.visibility = View.GONE
            else
                binding.recAttendees.visibility = View.VISIBLE
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
                    LinearLayoutManager.HORIZONTAL
                )
            )
        }

        for (model in itemData.Attendees) {
            section.add(ItemCorporateScheduleChild(model, followUpUiEvent))
        }
    }


    override fun getLayout(): Int {
        return R.layout.item_fragment_corporate_schedule_child
    }

}
