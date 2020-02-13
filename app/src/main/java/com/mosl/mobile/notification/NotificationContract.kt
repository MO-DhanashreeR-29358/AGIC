package com.mosl.mobile.notification

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.notification.NotificationResponse


interface NotificationContract : BaseView {

    fun render(uiModel: UiModel<List<NotificationResponse>>)
}