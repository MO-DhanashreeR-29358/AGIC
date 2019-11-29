package com.mosl.mobile.panelpager

import dagger.Module
import dagger.Provides

@Module
class PanelModule {

    @Provides
    internal fun ProvidePanelBaseFragment(fragment: PanelFragment): PanelContract {
        return fragment
    }
}