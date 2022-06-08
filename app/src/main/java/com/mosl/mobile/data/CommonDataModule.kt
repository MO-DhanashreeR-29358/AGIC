package com.mosl.mobile.data

import com.mosl.mobile.ApiBaseUrlImpl
import com.mosl.mobile.CertPinProviderImpl
import com.mosl.mobile.coredata.BaseDataModule
import com.mosl.mobile.coredata.CommonBaseUrls
import com.mosl.mobile.coredata.util.CertPinProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [BaseDataModule::class,BroadcastModule::class])
class CommonDataModule {
  @Provides
  @Singleton
  internal fun provideApiBaseUrl(): CommonBaseUrls {
    return ApiBaseUrlImpl()
  }

  @Provides
  internal fun provideCertPinnerProvider(): CertPinProvider {
    return CertPinProviderImpl
  }

}