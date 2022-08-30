package com.mosl.mobile.volunteer.corporateqr

import dagger.Module
import dagger.Provides

@Module
class QrCodeScanModule {

    @Provides
    internal fun provideQrCodeScanFragment(fragment: QrCodeScanFragment): QrCodeScanContract {
        return fragment
    }
}