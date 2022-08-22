package com.mosl.volunteer.notification

sealed class NotificationUiEvent {
    data class OnMoreClick(@JvmField val repsList: List<String>) : NotificationUiEvent()
}