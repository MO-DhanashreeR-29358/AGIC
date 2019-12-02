package com.mosl.mobile.panel

import com.mosl.app.core.base.BasePresenter
import dagger.Binds
import dagger.Module
import javax.inject.Inject

@Module
abstract class PanelChildModule {
    @Binds
    abstract fun providePanelChildView(fragment: PanelChildFragment): PanelChildContract
}

class PanelPresenter @Inject constructor(
    view: PanelChildContract
) : BasePresenter<PanelChildContract>(view) {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}