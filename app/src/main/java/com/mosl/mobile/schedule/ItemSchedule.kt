package com.mosl.mobile.schedule

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentScheduleBinding
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo
import com.mosl.shared.util.getTime
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.subjects.PublishSubject
import java.text.ParseException


class ItemSchedule(
    private val scheduleData: ScheduleResponseItemInfo,
    private val publishSubject: PublishSubject<ScheduleUiEvent>,
    val context: Context,
    val curentPos: Int
) :
    BindableItem<ItemFragmentScheduleBinding>(), Animation.AnimationListener, View.OnClickListener {

    lateinit var animBlink: Animation

    override fun getLayout(): Int {
        return R.layout.item_fragment_schedule
    }

    override fun bind(viewBinding: ItemFragmentScheduleBinding, position: Int) {

        viewBinding.apply {
            animBlink = AnimationUtils.loadAnimation(context, R.anim.blink_anim)

            animBlink.setAnimationListener(this@ItemSchedule)

            if (position == curentPos) {
                circleView.setBackgroundResource(R.drawable.bg_blue_circle)
                circleView.startAnimation(animBlink)

            } else {
                circleView.setBackgroundResource(R.drawable.bg_orange_circle)
            }

            tvScheduleNameMore.setOnClickListener(this@ItemSchedule)
            tvScheduleTime.setOnClickListener(this@ItemSchedule)

            try {

                if (scheduleData.Rep.size > 0) {
                    val scheduleName = scheduleData.Rep[0].NAME
                    val scheduleDesignation = scheduleData.Rep[0].DESIGNATION
                    tvScheduleName.text =
                        String.format("%s - %s", scheduleName, scheduleDesignation)
                }

                if (scheduleData.Rep.size > 1) {
                    tvScheduleNameMore.visibility = View.VISIBLE
                    val count = scheduleData.Rep.size - 1
                    tvScheduleNameMore.text = String.format("...%d More", count)
                }

                tvScheduleTime.text = getTime(scheduleData.startTime, scheduleData.endTime)
                tvScheduleCompanyName.text = scheduleData.companyName

                tvScheduleVenue.text = scheduleData.roomNo

                ivIconSchedule.apply {
                    when (scheduleData.natureOfMeeting) {
                        "1-1" -> setImageResource(R.drawable.ic_1x1)
                        "2-1" -> setImageResource(R.drawable.ic_2x1)
                        "3-1" -> setImageResource(R.drawable.ic_3x1)
                        "4-1" -> setImageResource(R.drawable.ic_4x1)
                        "Small Group" -> setImageResource(R.drawable.ic_group)
                        "Workshop" -> setImageResource(R.drawable.ic_mtrack)
                        else -> setImageResource(R.drawable.ic_mtrack)
                    }
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
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
        when {
            v?.id == R.id.tv_schedule_name_more -> publishSubject.onNext(
                ScheduleUiEvent.OnMoreClick(
                    scheduleData.Rep
                )
            )
            v?.id == R.id.tv_schedule_time -> publishSubject.onNext(
                ScheduleUiEvent.ItemClick(
                    scheduleData
                )
            )
        }
    }
}