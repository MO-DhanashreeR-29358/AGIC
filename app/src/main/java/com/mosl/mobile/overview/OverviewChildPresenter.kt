package com.mosl.mobile.overview

import com.mosl.app.core.base.BasePresenter
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class OverviewChildPresenter @Inject constructor(view: OverviewChildContract) :
    BasePresenter<OverviewChildContract>(view) {
    override fun start() {

        disposable.add(
            view.onTabClicked()
                .subscribe(view::renderPage)
        )
        disposable.add(
            view.onTabClickedClientWise()
                .subscribe(view::renderPageClientData)
        )

        view.onItemClicked()
            .subscribe {
                view.renderItemData(it.Rep)
            }.addTo(disposable)
    }
}