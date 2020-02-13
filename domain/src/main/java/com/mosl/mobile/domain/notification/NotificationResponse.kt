package com.mosl.mobile.domain.notification

data class NotificationResponse(
    @JvmField val corporateName: String,

    @JvmField val meetingDateTime: String,

    @JvmField val previousDateTime: String,

    @JvmField val type: String,

    @JvmField val message: String
)