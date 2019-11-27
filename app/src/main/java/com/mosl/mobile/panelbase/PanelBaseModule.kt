package com.mosl.mobile.panelbase

import dagger.Module
import dagger.Provides

@Module
class PanelBaseModule {

    @Provides
    internal fun ProvidePanelBaseFragment(fragment: PanelBaseFragment): PanelBaseView {
        return fragment
    }
}