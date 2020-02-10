package com.mosl.mobile.notification

import com.mosl.app.core.base.BasePresenter
import javax.inject.Inject

class NotificationPresenter  @Inject constructor(view : NotificationContract)
    :BasePresenter<NotificationContract>(view) {

    override fun start() {
    }
}