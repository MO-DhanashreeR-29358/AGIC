package com.mosl.mobile.corporateschedule

import com.mosl.app.core.base.BasePresenter
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CorporateScheduleChildPresenter @Inject constructor(view: CorporateChildContract) :
    BasePresenter<CorporateChildContract>(view) {
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