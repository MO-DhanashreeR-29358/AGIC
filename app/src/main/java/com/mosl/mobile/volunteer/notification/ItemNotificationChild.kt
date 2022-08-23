package com.mosl.mobile.volunteer.notification


import android.view.View
import com.mosl.mobile.volunteer.R
import com.mosl.mobile.volunteer.databinding.ItemNotificationChildBinding
import com.mosl.mobile.volunteer.domain.notification.NotificationResponse
import com.mosl.shared.AppDateFormats
import com.mosl.shared.util.getFormatedDate
import com.mosl.shared.util.getTimeAMPM
import com.xwray.groupie.databinding.BindableItem
import io.reactivex.subjects.PublishSubject
import java.util.*


class ItemNotificationChild(
    private val notificationData: NotificationResponse,
    private val publishSubject: PublishSubject<NotificationUiEvent>

) :
    BindableItem<ItemNotificationChildBinding>(), View.OnClickListener {


    override fun bind(viewBinding: ItemNotificationChildBinding, position: Int) {
        var currentDate = getFormatedDate(
            notificationData.meetingDateTime, AppDateFormats.DF_JSON_DATE_4,
            AppDateFormats.DF_DAY
        )
        currentDate = ordinalOf(currentDate.toInt())
        val currentTime = getTimeAMPM(notificationData.meetingDateTime)

        val curentDateTime = String.format(
            "%s %s", currentDate, currentTime
        )
        var previousDateTime: String = ""

        notificationData.previousDateTime?.apply {
            val previousDate = getFormatedDate(
                this, AppDateFormats.DF_JSON_DATE_4,
                AppDateFormats.DF_DAY
            )
            val previousTime = getTimeAMPM(this)

            previousDateTime = String.format(
                "%s %s", ordinalOf(previousDate.toInt()), previousTime
            )
        }



        viewBinding.apply {
            notificationData.clientList?.apply {
                if (this.size > 0) {
                    tvNewMore.visibility = View.VISIBLE
                    tvNewMore.text = String.format("(%d Reps)", (this.size))
                }
            }
            tvNewMore.setOnClickListener(this@ItemNotificationChild)
            when (notificationData.type.toLowerCase(Locale.ENGLISH)) {
                MEETING_CREATE -> {
                    groupNewMeeting.visibility = View.VISIBLE
                    groupPrevMeeting.visibility = View.GONE
                    tvMessage.visibility = View.GONE
                    tvNew.text = NEW_MEETING
                    tvNewCorporate.text = notificationData.corporateName
                    tvNewTime.text = curentDateTime

                }
                MEETING_UPDATE -> {

                    groupPrevMeeting.visibility = View.VISIBLE
                    groupNewMeeting.visibility = View.VISIBLE
                    tvMessage.visibility = View.GONE
                    tvNew.text = NEW
                    tvNewCorporate.text = notificationData.corporateName
                    tvNewTime.text = curentDateTime

                    tvPrevious.text = PREVIOUS
                    tvPrevCorporate.text = notificationData.corporateName
                    tvPrevTime.text = previousDateTime
                }
                MEETING_CANCEL -> {
                    groupPrevMeeting.visibility = View.VISIBLE
                    groupNewMeeting.visibility = View.GONE
                    tvMessage.visibility = View.GONE
                    tvPrevious.text = CANCELLED_MEETING
                    tvPrevCorporate.text = notificationData.corporateName
                    tvPrevTime.text = curentDateTime
                }
                else -> {
                    groupPrevMeeting.visibility = View.GONE
                    groupNewMeeting.visibility = View.GONE
                    tvMessage.visibility = View.VISIBLE
                    tvMessage.text = notificationData.message
                }
            }

        }
    }

    fun ordinalOf(i: Int) = "$i" + if (i % 100 in 11..13) "th" else when (i % 10) {
        1 -> "st"
        2 -> "nd"
        3 -> "rd"
        else -> "th"
    }

    override fun getLayout(): Int {
        return R.layout.item_notification_child
    }

    override fun onClick(v: View?) {
        when {
            v?.id == R.id.tv_new_more -> {
                notificationData.clientList?.apply {
                    publishSubject.onNext(
                        NotificationUiEvent.OnMoreClick(
                            this
                        )
                    )
                }

            }
        }
    }

    companion object {
        const val MEETING_CREATE = "meeting created"
        const val MEETING_UPDATE = "meeting updated"
        const val MEETING_CANCEL = "meeting deleted"
        const val CANCELLED_MEETING = "Cancelled Meeting"
        const val PREVIOUS = "Previous"
        const val NEW = "New"
        const val NEW_MEETING = "New Meeting"

    }
}
