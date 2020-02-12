package com.mosl.mobile.notification


import com.mosl.mobile.R
import com.mosl.mobile.databinding.ItemNotificationChildBinding
import com.mosl.mobile.domain.notification.NotificationResponse
import com.xwray.groupie.databinding.BindableItem


class ItemNotificationChild(
    private val notificationData: NotificationResponse
) :
    BindableItem<ItemNotificationChildBinding>() {


    override fun bind(viewBinding: ItemNotificationChildBinding, position: Int) {
        val meetingDate = notificationData.meetingDateTime
        val splitDate = meetingDate.split("T")

        viewBinding.apply {
            when (notificationData.type) {
                MEETING_CREATE -> {
                    tvNew.text = "New Meeting"
                    tvNewCorporate.text = notificationData.corporateName
                    tvNewDate.text = splitDate[0]
                    tvNewTime.text = splitDate[1]

                }
                MEETING_UPDATE -> {

                }
                MEETING_CANCEL -> {

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
