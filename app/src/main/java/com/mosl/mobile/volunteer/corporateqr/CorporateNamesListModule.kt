package com.mosl.mobile.volunteer.corporateqr

import dagger.Module
import dagger.Provides

@Module
class CorporateNamesListModule {

    @Provides
    internal fun provideCorporateNamesListFragment(fragment: CorporateNamesListFragment): CorporateNamesListContract {
        return fragment
    }
}