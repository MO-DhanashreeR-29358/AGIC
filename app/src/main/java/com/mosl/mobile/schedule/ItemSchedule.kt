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

        animBlink = AnimationUtils.loadAnimation(context, R.anim.blink_anim)

        animBlink.setAnimationListener(this)

        if (position == curentPos) {
            viewBinding.circleView.setBackgroundResource(R.drawable.bg_blue_circle)
            viewBinding.circleView.startAnimation(animBlink)

        } else {
            viewBinding.circleView.setBackgroundResource(R.drawable.bg_orange_circle)
        }

        viewBinding.tvScheduleNameMore.setOnClickListener(this@ItemSchedule)
        viewBinding.tvScheduleCompanyName.setOnClickListener(this@ItemSchedule)

        try {

            if (scheduleData.Rep.size > 0) {
                val scName = scheduleData.Rep[0].NAME
                val scDesi = scheduleData.Rep[0].DESIGNATION
                viewBinding.tvScheduleName.text = "$scName - $scDesi"
            }

            if (scheduleData.Rep.size > 1) {
                viewBinding.tvScheduleNameMore.visibility = View.VISIBLE
                val count = scheduleData.Rep.size - 1
                viewBinding.tvScheduleNameMore.text = "...$count More"
            }

            viewBinding.tvScheduleTime.text = getTime(scheduleData.startTime,scheduleData.endTime)
            viewBinding.tvScheduleCompanyName.text = scheduleData.companyName

            viewBinding.tvScheduleVenue.text = scheduleData.roomNo

            if (scheduleData.natureOfMeeting.equals("1-1"))
                viewBinding.ivIconSchedule.setImageResource(R.drawable.ic_1x1)
            else if (scheduleData.natureOfMeeting.equals("2-1"))
                viewBinding.ivIconSchedule.setImageResource(R.drawable.ic_2x1)
            else if (scheduleData.natureOfMeeting.equals("3-1"))
                viewBinding.ivIconSchedule.setImageResource(R.drawable.ic_3x1)
            else if (scheduleData.natureOfMeeting.equals("4-1"))
                viewBinding.ivIconSchedule.setImageResource(R.drawable.ic_4x1)
            else if (scheduleData.natureOfMeeting.equals("Small Group"))
                viewBinding.ivIconSchedule.setImageResource(R.drawable.ic_group)
            else
                viewBinding.ivIconSchedule.setImageResource(R.drawable.ic_mtrack)

        } catch (e: ParseException) {
            e.printStackTrace()
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
            v?.id == R.id.tv_schedule_company_name -> publishSubject.onNext(
                ScheduleUiEvent.ItemClick(
                    scheduleData
                )
            )
        }
    }
}