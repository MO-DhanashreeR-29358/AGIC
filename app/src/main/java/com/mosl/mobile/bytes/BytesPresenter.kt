package com.mosl.mobile.bytes

import com.mosl.app.core.base.BasePresenter
import com.mosl.app.core.prefs.StringPreference
import com.mosl.mobile.annotations.LastBytesDate
import com.mosl.mobile.annotations.LastScheduleDate
import com.mosl.mobile.app_user.login.UserManager
import com.mosl.mobile.app_user.login.user
import com.mosl.mobile.domain.bytes.BytesRepository
import com.mosl.mobile.domain.bytes.BytesRequest
import com.mosl.shared.AppDateFormats
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import java.util.*
import javax.inject.Inject


class BytesPresenter @Inject constructor(
    view: BytesContract,
    val repository: BytesRepository


) : BasePresenter<BytesContract>(view) {

    override fun start() {


        Observable.merge(view.swipeRefresh(), onFetchCalled(), onRetryCalled()).switchMap {
            repository.bytesData()
        }.compose(applyUiModel())
            .subscribe {
                view.renderData(it)
            }.addTo(disposable)

    }



}