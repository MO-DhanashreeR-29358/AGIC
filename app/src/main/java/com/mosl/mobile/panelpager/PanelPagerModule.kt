package com.mosl.mobile.panelpager

import dagger.Module
import dagger.Provides

@Module
class PanelPagerModule {

    @Provides
    internal fun ProvidePanelBaseFragment(fragment: PanelPagerFragment): PanelPagerView {
        return fragment
    }
}