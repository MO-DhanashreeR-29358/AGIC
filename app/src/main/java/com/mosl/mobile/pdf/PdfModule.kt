package com.mosl.mobile.pdf

import dagger.Module
import dagger.Provides

@Module
class PdfModule {

    @Provides
    internal fun ProvidePdfFragment(fragment: PdfFragment): PdfContract {
        return fragment
    }
}