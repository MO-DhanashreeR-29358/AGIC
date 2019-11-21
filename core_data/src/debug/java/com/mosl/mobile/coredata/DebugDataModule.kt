package com.mosl.mobile.coredata

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.mosl.mobile.app_user.util.AuthorizationInterceptor
import com.mosl.mobile.coredata.util.CertPinProvider
import com.mosl.mobile.coredata.util.HttpListenerFactory
import com.mosl.mobile.coredata.util.interceptors.CacheInterceptor
import com.mosl.mobile.coredata.util.interceptors.EncodingInterceptor
import com.mosl.mobile.coredata.util.interceptors.HeaderInterceptor
import dagger.Module
import dagger.Provides
import mosl.trading.annotation.InternalAPIClient
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Named
import javax.inject.Singleton

@Module
class DebugDataModule {

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }.setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
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
        encodingInterceptor: EncodingInterceptor
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(encodingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @InternalAPIClient
    internal fun provideInternalAPIOkHttpClient(
        @Named("BaseOkClient") okHttpClient: OkHttpClient,
        headerInterceptor: HeaderInterceptor,
        authorizationInterceptor: AuthorizationInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        encodingInterceptor: EncodingInterceptor
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(encodingInterceptor)
            .build()
    }
}