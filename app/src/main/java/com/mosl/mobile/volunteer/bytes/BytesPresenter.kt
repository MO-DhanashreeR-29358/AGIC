package com.mosl.mobile.volunteer.bytes

import com.mosl.app.core.base.BasePresenter
import com.mosl.mobile.volunteer.domain.bytes.BytesRepository
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


class BytesPresenter @Inject constructor(
    view: BytesContract,
    val repository: BytesRepository


) : BasePresenter<BytesContract>(view) {

    override fun start() {


        Observable.merge(view.swipeRefresh(), onFetchCalled(), onRetryCalled()).switchMap {
            repository.bytesData()
                .compose(applyUiModel())
        }.subscribe {
                view.renderData(it)
            }.addTo(disposable)

    }



}