package com.mosl.mobile.volunteer.notification

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.volunteer.domain.notification.NotificationResponse
import io.reactivex.Observable


interface NotificationContract : BaseView {
    fun swipeRefresh(): Observable<Unit>
    fun renderData(uiModel: UiModel<List<NotificationResponse>>)
    fun onItemClicked(): Observable<NotificationUiEvent.OnMoreClick>
    fun renderItemData(repList: List<String>)
}