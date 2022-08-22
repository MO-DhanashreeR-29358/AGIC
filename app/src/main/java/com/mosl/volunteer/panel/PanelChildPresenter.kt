package com.mosl.volunteer.panel

import com.mosl.app.core.base.BasePresenter
import dagger.Binds
import dagger.Module
import io.reactivex.rxkotlin.addTo
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