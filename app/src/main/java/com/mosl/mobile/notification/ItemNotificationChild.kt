package com.mosl.mobile.notification


import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemNotificationChildBinding
import com.mosl.mobile.domain.notification.NotificationResponse
import com.mosl.shared.util.getDateJSON_DATE6
import com.mosl.shared.util.getTimeAMPM
import com.xwray.groupie.databinding.BindableItem
import java.util.*


class ItemNotificationChild(
    private val notificationData: NotificationResponse
) :
    BindableItem<ItemNotificationChildBinding>() {


    override fun bind(viewBinding: ItemNotificationChildBinding, position: Int) {
        val date = getDateJSON_DATE6(notificationData.meetingDateTime)
        val time = getTimeAMPM(notificationData.meetingDateTime)

        viewBinding.apply {
            when (notificationData.type.toLowerCase(Locale.ENGLISH)) {
                MEETING_CREATE -> {
                    groupNewMeeting.visibility = View.VISIBLE
                    groupPrevMeeting.visibility = View.GONE
                    tvMessage.visibility = View.GONE
                    tvNew.text = NEW_MEETING
                    tvNewCorporate.text = notificationData.corporateName
                    tvNewDate.text = date
                    tvNewTime.text = time

                }
                MEETING_UPDATE -> {

                    groupPrevMeeting.visibility = View.VISIBLE
                    groupNewMeeting.visibility = View.VISIBLE
                    tvMessage.visibility = View.GONE
                    tvNew.text = NEW
                    tvNewCorporate.text = notificationData.corporateName
                    tvNewDate.text = date
                    tvNewTime.text = time

                    tvPrevious.text = PREVIOUS
                    tvPrevCorporate.text = notificationData.corporateName
                    tvPrevDate.text = getDateJSON_DATE6(notificationData.previousDateTime?:"2020-03-01T00:00:00")
                    tvPrevTime.text = getTimeAMPM(notificationData.previousDateTime?:"2020-03-01T00:00:00")
                }
                MEETING_CANCEL -> {
                    groupPrevMeeting.visibility = View.VISIBLE
                    groupNewMeeting.visibility = View.GONE
                    tvMessage.visibility = View.GONE
                    tvPrevious.text = CANCELLED_MEETING
                    tvPrevCorporate.text = notificationData.corporateName
                    tvPrevDate.text = date
                    tvPrevTime.text = time
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

    override fun getLayout(): Int {
        return R.layout.item_notification_child
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
