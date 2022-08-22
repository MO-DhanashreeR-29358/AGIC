package com.mosl.volunteer.coredata

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.mosl.volunteer.coredata.interceptor.EncryptionInterceptor
import com.mosl.volunteer.coredata.util.CertPinProvider
import com.mosl.volunteer.coredata.util.HttpListenerFactory
import com.mosl.volunteer.coredata.util.interceptors.CacheInterceptor
import com.mosl.volunteer.coredata.util.interceptors.HeaderInterceptor
import dagger.Module
import dagger.Provides
import mosl.trading.annotation.InternalAPIClient
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
class DebugDataModule {

  @Provides
  @Singleton
  internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
  }

  @Provides
  @Singleton
  internal fun provideStethoInterceptor(): StethoInterceptor {
    return StethoInterceptor()
  }

  @Provides
  @Singleton
  @Named("BaseOkClient")
  internal fun provideBaseOkHttpClient(
    cacheInterceptor: CacheInterceptor,
    stethoInterceptor: StethoInterceptor,
    listenerFactory: HttpListenerFactory,
    okHttpCache: Cache,
    pinProvider: CertPinProvider
  ): OkHttpClient {
    return DataModule.createOkHttpClient(pinProvider.getPinner())
      .addNetworkInterceptor(cacheInterceptor)
      .addNetworkInterceptor(stethoInterceptor)
      .cache(okHttpCache)
      .eventListenerFactory(listenerFactory)
      .build()
  }

  @Provides
  @Singleton
  internal fun provideOkHttpClient(
    @Named("BaseOkClient") okHttpClient: OkHttpClient,
    loggingInterceptor: HttpLoggingInterceptor,
    encryptionInterceptor: EncryptionInterceptor
  ): OkHttpClient {
    return okHttpClient.newBuilder()
      .addInterceptor(loggingInterceptor)
      .addInterceptor(encryptionInterceptor)
      .build()
  }

  @Provides
  @Singleton
  @InternalAPIClient
  internal fun provideInternalAPIOkHttpClient(
    @Named("BaseOkClient") okHttpClient: OkHttpClient,
    headerInterceptor: HeaderInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
    encryptionInterceptor: EncryptionInterceptor
  ): OkHttpClient {
    return okHttpClient.newBuilder()
      .addInterceptor(headerInterceptor)
      .addInterceptor(loggingInterceptor)
      .addInterceptor(encryptionInterceptor)
      .addInterceptor(loggingInterceptor)
      .build()
  }
}