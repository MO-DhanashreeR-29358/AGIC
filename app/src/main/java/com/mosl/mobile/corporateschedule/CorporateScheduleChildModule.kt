package com.mosl.mobile.corporateschedule

import dagger.Module
import dagger.Provides

@Module
class CorporateScheduleChildModule {

    @Provides
    internal fun ProvideCorporateScheduleFragment(fragment: CorporateScheduleFragment): CorporateChildContract {
        return fragment
    }
}