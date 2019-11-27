package com.mosl.mobile.bytes

import com.mosl.app.core.base.BasePresenter

import javax.inject.Inject


class BytesPresenter @Inject constructor(
    view: BytesContract
) : BasePresenter<BytesContract>(view) {
    private val repository: BytesRepository = BytesRepositoryImpl

    override fun start() {

    }

}