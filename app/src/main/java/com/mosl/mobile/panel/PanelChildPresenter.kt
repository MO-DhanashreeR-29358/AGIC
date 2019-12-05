package com.mosl.mobile.panel

import com.mosl.app.core.base.BasePresenter
import dagger.Binds
import dagger.Module
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
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

        disposable.add(onFetchCalled()
            .map {
                view.panelDayList()
            }
            .subscribe(view::renderPage))

    }
}