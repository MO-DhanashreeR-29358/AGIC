package com.mosl.volunteer.domain.notification

data class NotificationResponse(
    @JvmField val corporateName: String,

    @JvmField val meetingDateTime: String,

    @JvmField val previousDateTime: String?,

    @JvmField val type: String,

    @JvmField val message: String,
    @JvmField val clientList: List<String>?
)

data class NotificationItemModel(
    @JvmField val corporateName: String,
    @JvmField  var meetingDateTime:String,
    @JvmField  var previousDateTime:String?,
    @JvmField val type: String,
    @JvmField val message: String
)

data class NotificationModel(
    @JvmField val headerTime: String,
    @JvmField val itemList: List<NotificationItemModel>
)

