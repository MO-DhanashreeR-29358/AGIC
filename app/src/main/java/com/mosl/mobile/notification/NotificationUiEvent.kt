package com.mosl.mobile.notification

sealed class NotificationUiEvent {
    data class OnMoreClick(@JvmField val repsList: List<String>) : NotificationUiEvent()
}