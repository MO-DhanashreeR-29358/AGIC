package com.mosl.mobile.notification


import android.view.View
import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemNotificationChildBinding
import com.mosl.mobile.domain.notification.NotificationResponse
import com.mosl.shared.util.getDateJSON_DATE6
import com.mosl.shared.util.getTimeAMPM
import com.xwray.groupie.databinding.BindableItem


class ItemNotificationChild(
    private val notificationData: NotificationResponse
) :
    BindableItem<ItemNotificationChildBinding>() {


    override fun bind(viewBinding: ItemNotificationChildBinding, position: Int) {
        val date = getDateJSON_DATE6(notificationData.meetingDateTime)
        val time = getTimeAMPM(notificationData.meetingDateTime)

        viewBinding.apply {
            when (notificationData.type) {
                MEETING_CREATE -> {
                    groupNewMeeting.visibility = View.VISIBLE
                    groupPrevMeeting.visibility = View.GONE
                    tvMessage.visibility = View.GONE
                    tvNew.text = "New Meeting"
                    tvNewCorporate.text = notificationData.corporateName
                    tvNewDate.text = date
                    tvNewTime.text = time

                }
                MEETING_UPDATE -> {

                    groupPrevMeeting.visibility = View.VISIBLE
                    groupNewMeeting.visibility = View.VISIBLE
                    tvMessage.visibility = View.GONE
                    tvNew.text = "New"
                    tvNewCorporate.text = notificationData.corporateName
                    tvNewDate.text = date
                    tvNewTime.text = time

                    tvPrevious.text = "Previous"
                    tvPrevCorporate.text = notificationData.corporateName
                    tvPrevDate.text = getDateJSON_DATE6(notificationData.previousDateTime)
                    tvPrevTime.text = getTimeAMPM(notificationData.previousDateTime)
                }
                MEETING_CANCEL -> {
                    groupPrevMeeting.visibility = View.VISIBLE
                    groupNewMeeting.visibility = View.GONE
                    tvMessage.visibility = View.GONE
                    tvPrevious.text = "Cancelled Meeting"
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
        const val MEETING_CREATE = "MeetingCreate"
        const val MEETING_UPDATE = "MeetingUpdated"
        const val MEETING_CANCEL = "MeetingCancelled"

    }
}
