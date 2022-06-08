package com.mosl.mobile.notification

import com.mosl.app.core.base.BasePresenter
import com.mosl.commons.rx.addTo
import com.mosl.mobile.appuser.login.UserManager
import com.mosl.mobile.appuser.login.user
import com.mosl.mobile.domain.notification.NotificationRepository
import com.mosl.mobile.domain.notification.NotificationRequestInfo
import io.reactivex.Observable
import javax.inject.Inject

class NotificationPresenter @Inject constructor(
    view: NotificationContract,
    val repository: NotificationRepository,
    val userManager: UserManager
) : BasePresenter<NotificationContract>(view) {

    override fun start() {

        val objNotificationRequestInfo = NotificationRequestInfo(
            ClientID = userManager.user.empCode,
            Role = userManager.user.roomNo
        )

        Observable.merge(view.swipeRefresh(), onFetchCalled(), onRetryCalled())
            .switchMap {
                repository.getNotificationData(objNotificationRequestInfo)
                    .compose(applyUiModel())
            }
            .subscribe {
                view.renderData(it)
            }.addTo(disposable)

        view.onItemClicked().subscribe {
            view.renderItemData(it.repsList)
        }.addTo(disposable)


    }

}