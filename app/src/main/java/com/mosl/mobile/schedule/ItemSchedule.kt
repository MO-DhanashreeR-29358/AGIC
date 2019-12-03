package com.mosl.mobile.schedule

import android.content.Context
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemFragmentScheduleBinding
import com.xwray.groupie.databinding.BindableItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.mosl.mobile.domain.schedule.ScheduleRequestInfo
import com.mosl.mobile.domain.schedule.ScheduleResponseItemInfo


class ItemSchedule(private val scheduleData: ScheduleResponseItemInfo, val context: Context) :
    BindableItem<ItemFragmentScheduleBinding>(), Animation.AnimationListener {


    lateinit var animBlink: Animation
    override fun getLayout(): Int {
        return R.layout.item_fragment_schedule
    }

    override fun bind(viewBinding: ItemFragmentScheduleBinding, position: Int) {
        animBlink = AnimationUtils.loadAnimation(
            context,
            R.anim.blink_anim
        )
        animBlink.setAnimationListener(this)
        if (position == 1) {
            viewBinding.circleView.setBackgroundResource(R.drawable.bg_blue_circle)
            viewBinding.circleView.startAnimation(animBlink)

        } else {
            viewBinding.circleView.setBackgroundResource(R.drawable.bg_orange_circle)
        }
        viewBinding.tvScheduleTime.text = scheduleData.start_time
        viewBinding.tvScheduleDesignation.text = scheduleData.Rep[0].DESIGNATION
        viewBinding.tvScheduleName.text = scheduleData.Rep[0].NAME
        viewBinding.tvScheduleVenue.text = scheduleData.Schedule_Date
    }

    override fun onAnimationRepeat(animation: Animation?) {
        if (animation == animBlink) {
            animation.start()
        }

    }

    override fun onAnimationEnd(animation: Animation?) {
        if (animation == animBlink) {
            animation.start()
        }

    }

    override fun onAnimationStart(animation: Animation?) {
        if (animation == animBlink) {
            animation.start()
        }
    }
}