package com.mosl.mobile.notification

import com.mosl.app.core.base.BaseView
import com.mosl.commons.data.UiModel
import io.reactivex.Observable

interface NotificationContract :BaseView {

    fun render(uiModel:UiModel<String>)
}