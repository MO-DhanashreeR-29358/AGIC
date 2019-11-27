package com.mosl.mobile.bytes

import dagger.Module
import dagger.Provides

@Module
class BytesModule {

    @Provides
    internal fun ProvideBytesFragment(fragment: BytesFragment): BytesContract {
        return fragment
    }
}