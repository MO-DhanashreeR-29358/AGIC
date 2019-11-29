package com.mosl.mobile.panel

import com.mosl.app.core.base.BasePresenter
import dagger.Binds
import dagger.Module
import javax.inject.Inject

@Module
abstract class PanelChildModule {
    @Binds
    abstract fun providePanelChildView(fragment: PanelChildFragment): PanelChildContract.View
}

class PanelPresenter @Inject constructor(
    view: PanelChildContract.View
) : BasePresenter<PanelChildContract.View>(view) {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}