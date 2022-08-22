package com.mosl.volunteer.data

import com.mosl.volunteer.ApiBaseUrlImpl
import com.mosl.volunteer.CertPinProviderImpl
import com.mosl.volunteer.coredata.BaseDataModule
import com.mosl.volunteer.coredata.CommonBaseUrls
import com.mosl.volunteer.coredata.util.CertPinProvider
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