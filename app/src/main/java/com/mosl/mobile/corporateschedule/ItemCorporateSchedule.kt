package com.mosl.mobile.corporateschedule

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosl.groupiex.section.DataManagerSection
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentCorporateScheduleChildBinding
import com.mosl.mobile.domain.corporateschedulepager.CorporateScheduleResponseDayItemInfo
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.subjects.PublishSubject

class ItemCorporateSchedule(
    scheduleData: CorporateScheduleResponseDayItemInfo,
    private val publishSubject: PublishSubject<CorporateScheduleUiEvent>,
    private val currentPosition: Int,
    val context: Context,
    private val expandPosition: Int
) :
    BindableItem<ItemFragmentCorporateScheduleChildBinding>(), Animation.AnimationListener,
    View.OnClickListener {
    private val itemData = scheduleData

    lateinit var animBlink: Animation

    lateinit var binding: ItemFragmentCorporateScheduleChildBinding

    override fun bind(viewBinding: ItemFragmentCorporateScheduleChildBinding, position: Int) {

        viewBinding.apply {

            animBlink = AnimationUtils.loadAnimation(context, R.anim.blink_anim)

            animBlink.setAnimationListener(this@ItemCorporateSchedule)

            tvShiftTime.text = (itemData.shiftTiming)
            tvRoomNumber.text = String.format("Room No - %s", itemData.roomNumber)

            imageView.apply {
                when (itemData.natureOfMeeting) {
                    "1-1" -> setImageResource(R.drawable.ic_1x1_small_h)
                    "2-1" -> setImageResource(R.drawable.ic_2x1_small_h)
                    "3-1" -> setImageResource(R.drawable.ic_3x1_small_h)
                    "4-1" -> setImageResource(R.drawable.ic_4x1_small_h)
                    "Small Group" -> setImageResource(R.drawable.ic_group_small_h)
                    "Workshop" -> setImageResource(R.drawable.ic_mtrack_small_h)
                    else -> setImageResource(R.drawable.ic_mtrack_small_h)
                }
            }

            if (currentPosition == position) {
                rabView.setBackgroundResource(R.drawable.bg_gif_orange_circle)
                rabView.startAnimation(animBlink)
                recAttendees.visibility = View.VISIBLE
            } else if (expandPosition == position) {
                rabView.setBackgroundResource(R.drawable.bg_orange_circle)
                recAttendees.visibility = View.VISIBLE
            } else {
                rabView.setBackgroundResource(R.drawable.bg_light_orange_circle)
                recAttendees.visibility = View.GONE
            }
            binding = viewBinding
            setupRecyclerView(viewBinding)
        }
    }

    @JvmField
    protected val onRetryClick = View.OnClickListener {

    }

    private fun setupRecyclerView(binding: ItemFragmentCorporateScheduleChildBinding) {

        binding.tvRoomNumber.setOnClickListener(this)
        binding.imageView.setOnClickListener(this)
        binding.tvShiftTime.setOnClickListener(this)
        binding.rabView.setOnClickListener(this)

        val groupAdapter = GroupAdapter<GroupieViewHolder>()

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

    override fun onAnimationRepeat(animation: Animation) {
        if (animation == animBlink) {
            animation.start()
        }
    }

    override fun onAnimationEnd(animation: Animation) {
        if (animation == animBlink) {
            animation.start()
        }
    }

    override fun onAnimationStart(animation: Animation) {
        if (animation == animBlink) {
            animation.start()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_roomNumber, R.id.imageView, R.id.tv_shiftTime, R.id.rabView -> {
                if (binding.recAttendees.visibility == View.VISIBLE) {
                    binding.recAttendees.visibility = View.GONE
                    binding.rabView.setBackgroundResource(R.drawable.bg_light_orange_circle)
                } else {
                    binding.recAttendees.visibility = View.VISIBLE
                    binding.rabView.setBackgroundResource(R.drawable.bg_orange_circle)
                }
            }
        }
    }

}
