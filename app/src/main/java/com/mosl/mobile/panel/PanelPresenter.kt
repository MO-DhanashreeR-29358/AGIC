package com.mosl.mobile.panel

import com.mosl.app.core.base.BasePresenter
import dagger.Binds
import dagger.Module
import javax.inject.Inject

@Module
abstract class PanelModule {
    @Binds
    abstract fun provideDashboardView(fragment: PanelFragment): PanelContract.View
}

class PanelPresenter @Inject constructor(
    view: PanelContract.View
) : BasePresenter<PanelContract.View>(view) {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}