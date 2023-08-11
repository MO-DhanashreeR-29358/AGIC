package com.mosl.mobile.idcard

import dagger.Module
import dagger.Provides

@Module
class IDCardModule {
    @Provides
    internal fun ProvideIDCardFragment(fragment: IdCardFragment): IDCardContract {
        return fragment
    }
}