package com.mosl.mobile.rm.panel

import com.mosl.app.core.base.BasePresenter
import com.mosl.app.core.exception.ServiceFailureException
import com.mosl.mobile.rm.panelpager.PanelTabChangeEvent
import dagger.Binds
import dagger.Module
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.ofType
import javax.inject.Inject

@Module
abstract class PanelChildModule {
    @Binds
    abstract fun providePanelChildView(fragment: PanelChildFragment): PanelChildView
}

class PanelChildPresenter @Inject constructor(
    view: PanelChildView
) : BasePresenter<PanelChildView>(view) {
    override fun start() {

        view.onTabClicked()
            .subscribe(view::renderPage)
            .addTo(disposable)

        view.onItemClicked()
            .subscribe {
                view.renderItemToCalender(it.panelDayResponseItem)
            }
            .addTo(disposable)
    }
}