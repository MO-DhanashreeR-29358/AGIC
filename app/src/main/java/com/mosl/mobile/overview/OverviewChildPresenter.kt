package com.mosl.mobile.overview

import com.mosl.app.core.base.BasePresenter
import com.mosl.app.core.exception.ServiceFailureException
import javax.inject.Inject

class OverviewChildPresenter @Inject constructor(view: OverviewChildContract) :
    BasePresenter<OverviewChildContract>(view) {
    override fun start() {

        disposable.add(view.onTabClicked()
            .map {
                if (it.overviewDayWiseListData.isEmpty()) {
                    throw ServiceFailureException("No data available for the day")
                } else {
                    it.overviewDayWiseListData
                }
            }
            .compose(applyUiModel())
            .subscribe(view::renderPage))
    }
}