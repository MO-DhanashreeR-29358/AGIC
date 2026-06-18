package com.mosl.mobile.rm.notification

sealed class NotificationUiEvent {
    data class OnMoreClick(@JvmField val repsList: List<String>) : NotificationUiEvent()
}