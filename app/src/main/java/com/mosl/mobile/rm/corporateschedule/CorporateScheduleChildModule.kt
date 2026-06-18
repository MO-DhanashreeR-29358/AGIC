package com.mosl.mobile.rm.corporateschedule

import dagger.Module
import dagger.Provides

@Module
class CorporateScheduleChildModule {

    @Provides
    internal fun ProvideCorporateScheduleFragment(fragment: CorporateScheduleFragment): CorporateChildContract {
        return fragment
    }
}