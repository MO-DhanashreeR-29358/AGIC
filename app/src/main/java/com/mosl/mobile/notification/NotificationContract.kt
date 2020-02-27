package com.mosl.mobile.notification

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.notification.NotificationModel
import com.mosl.mobile.domain.notification.NotificationResponse
import io.reactivex.Observable


interface NotificationContract : BaseView {
    fun swipeRefresh(): Observable<Unit>
    fun renderData(uiModel: UiModel<List<NotificationResponse>>)
}