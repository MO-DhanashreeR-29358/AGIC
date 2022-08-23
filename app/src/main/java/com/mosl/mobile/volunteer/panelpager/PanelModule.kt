package com.mosl.mobile.volunteer.panelpager

import com.mosl.mobile.volunteer.data.panel.PanelDetailsRepositoryImpl
import com.mosl.mobile.volunteer.domain.panel.PanelDetailsRepository
import dagger.Module
import dagger.Provides

@Module
class PanelModule {

    @Provides
    internal fun ProvidePanelBaseFragment(fragment: PanelFragment): PanelView {
        return fragment
    }

    @Provides
    internal  fun ProvidesPanelDetailsResitory(repository: PanelDetailsRepositoryImpl):PanelDetailsRepository{
        return repository
    }
}