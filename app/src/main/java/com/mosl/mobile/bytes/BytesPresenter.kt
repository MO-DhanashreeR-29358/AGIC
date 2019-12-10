package com.mosl.mobile.bytes

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.domain.bytes.BytesRepository
import com.mosl.mobile.domain.bytes.BytesRequest
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


class BytesPresenter @Inject constructor(
    view: BytesContract,
    val repository: BytesRepository,
    val userManager: UserManager
) : BasePresenter<BytesContract>(view) {

    override fun start() {


        Observable.merge(view.swipeRefresh(), onFetchCalled(), onRetryCalled()).switchMap {
            repository.bytesData(BytesRequest("New", "0", userManager.user.clientId))
        }.compose(applyUiModel())
            .subscribe {
                view.renderData(it)
            }.addTo(disposable)

    }

}