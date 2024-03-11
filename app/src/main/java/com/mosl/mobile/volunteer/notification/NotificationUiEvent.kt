package com.mosl.mobile.volunteer.notification

sealed class NotificationUiEvent {
    data class OnMoreClick(@JvmField val repsList: List<String>) : NotificationUiEvent()
}