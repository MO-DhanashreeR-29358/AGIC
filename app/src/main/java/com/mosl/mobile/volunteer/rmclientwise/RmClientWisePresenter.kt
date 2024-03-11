package com.mosl.mobile.volunteer.rmclientwise

import com.mosl.app.core.base.BasePresenter
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class RmClientWisePresenter @Inject constructor(view: RmClientWiseContract) :
    BasePresenter<RmClientWiseContract>(view) {
    override fun start() {

        disposable.add(
            view.onTabClicked()
                .subscribe(view::renderPage)
        )

        view.onItemClicked()
            .subscribe {
                view.renderItemData(it.Rep)
            }.addTo(disposable)
    }
}