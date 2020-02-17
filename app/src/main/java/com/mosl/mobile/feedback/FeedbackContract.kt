package com.mosl.mobile.feedback

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import com.mosl.mobile.domain.notification.NotificationResponse


interface FeedbackContract : BaseView {

    fun render(uiModel: UiModel<List<NotificationResponse>>)
}