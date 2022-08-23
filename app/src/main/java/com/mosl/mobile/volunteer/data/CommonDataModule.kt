package com.mosl.mobile.volunteer.data

import com.mosl.mobile.volunteer.ApiBaseUrlImpl
import com.mosl.mobile.volunteer.CertPinProviderImpl
import com.mosl.mobile.volunteer.coredata.BaseDataModule
import com.mosl.mobile.volunteer.coredata.CommonBaseUrls
import com.mosl.mobile.volunteer.coredata.util.CertPinProvider
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