package com.mosl.mobile.notification

import com.mosl.app.core.base.BasePresenter
import com.mosl.commons.rx.addTo
import com.mosl.mobile.domain.notification.NotificationRepository
import javax.inject.Inject

class NotificationPresenter @Inject constructor(
    view: NotificationContract,
    val repository: NotificationRepository
) : BasePresenter<NotificationContract>(view) {

    override fun start() {
        onFetchCalled()
            .switchMap {
                repository.getNotificationData()
            }
            .compose(applyUiModel())
            .subscribe {
             view.render(it)
            }.addTo(disposable)
    }
}