package com.mosl.mobile.coredata

import com.mosl.mobile.coredata.interceptor.EncryptionInterceptor
import com.mosl.mobile.coredata.util.CertPinProvider
import com.mosl.mobile.coredata.util.HttpListenerFactory
import com.mosl.mobile.coredata.util.interceptors.ApiLoggerInterceptor
import com.mosl.mobile.coredata.util.interceptors.CacheInterceptor
import com.mosl.mobile.coredata.util.interceptors.HeaderInterceptor
import dagger.Module
import dagger.Provides
import mosl.trading.annotation.InternalAPIClient
import okhttp3.Cache
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {


    companion object {
        fun createOkHttpClient(pinner: CertificatePinner?): OkHttpClient.Builder {
            val builder = OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
//            if (pinner != null) {
//                builder.certificatePinner(pinner)
//            }
            return builder
        }
    }


    @Provides
    @Singleton
    @Named("BaseOkClient")
    internal fun provideBaseOkHttpClient(
        cacheInterceptor: CacheInterceptor,
        listenerFactory: HttpListenerFactory,
        okHttpCache: Cache,
        pinProvider: CertPinProvider,
        apiLoggerInterceptor: ApiLoggerInterceptor
    ): OkHttpClient {
        return createOkHttpClient(pinProvider.getPinner())
            .addNetworkInterceptor(cacheInterceptor)
            .addNetworkInterceptor(apiLoggerInterceptor)
            .cache(okHttpCache)
            .eventListenerFactory(listenerFactory)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        @Named("BaseOkClient") okHttpClient: OkHttpClient,
        encryptionInterceptor: EncryptionInterceptor
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor(encryptionInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @InternalAPIClient
    internal fun provideInternalAPIOkHttpClient(
        @Named("BaseOkClient") okHttpClient: OkHttpClient,
        headerInterceptor: HeaderInterceptor,
        encryptionInterceptor: EncryptionInterceptor
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(encryptionInterceptor)
            .build()
    }


}