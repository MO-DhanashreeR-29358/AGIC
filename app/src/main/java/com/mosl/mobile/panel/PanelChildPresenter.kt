package com.mosl.mobile.panel

import com.mosl.app.core.base.BasePresenter
import com.mosl.app.core.exception.ServiceFailureException
import dagger.Binds
import dagger.Module
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@Module
abstract class PanelChildModule {
    @Binds
    abstract fun providePanelChildView(fragment: PanelChildFragment): PanelChildView
}

class PanelPresenter @Inject constructor(
    view: PanelChildView
) : BasePresenter<PanelChildView>(view) {
    override fun start() {

        disposable.add(view.onTabClicked()
            .map {
                if (it.panelDayWiseListData.isEmpty()){
                    throw ServiceFailureException("No data available for the day")
                }else {
                    it.panelDayWiseListData
                }
            }
            .compose (applyUiModel())
            .subscribe(view::renderPage))

        view.onItemClicked()
            .subscribe {
                view.renderItemToCalender(it.panelDayResponseItem)
            }
            .addTo(disposable)
    }
}