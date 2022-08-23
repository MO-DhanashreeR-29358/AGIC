package com.mosl.mobile.volunteer.rmclientwise

import dagger.Module
import dagger.Provides

@Module
class RmClientWiseModule {

    @Provides
    internal fun ProvideRmClientWiseFragment(fragment: RmClientWiseFragment): RmClientWiseContract {
        return fragment
    }
}