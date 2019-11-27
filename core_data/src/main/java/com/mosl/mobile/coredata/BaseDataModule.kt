package com.mosl.mobile.coredata

import android.content.Context
import com.mosl.app.core.rx.DefaultDispatchers
import com.mosl.commons.rx.Dispatchers
import com.mosl.mobile.coredata.util.HttpListenerFactory
import dagger.Module
import dagger.Provides
import mosl.trading.annotation.InternalAPIRetrofit
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.protobuf.ProtoConverterFactory
import timber.log.Timber
import java.io.File
import javax.inject.Singleton

@Module(includes = [PreferenceDataModule::class])
class BaseDataModule {

  @Provides
  internal fun provideDispatchers(): Dispatchers {
    return DefaultDispatchers
  }

  @Provides
  @Singleton
  internal fun getRxJava2CallAdapter(dispatchers: Dispatchers): RxJava2CallAdapterFactory {
    return RxJava2CallAdapterFactory.createWithScheduler(dispatchers.io)
  }

  @Provides
  @Singleton
  @InternalAPIRetrofit
  internal fun provideNewRetrofitBuilder(client: OkHttpClient,
      moslResponseWrapper: MOSLResponseWrapper,
      rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit.Builder {
    return Retrofit.Builder().client(client)
        .addConverterFactory(moslResponseWrapper)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(rxJava2CallAdapterFactory)

  }

  @Provides
  @Singleton
  internal fun provideRetrofitBuilder(client: OkHttpClient,
    moslResponseWrapper: MOSLResponseWrapper,
      rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit
  .Builder {
    return Retrofit.Builder().client(client)
      .addConverterFactory(moslResponseWrapper)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
  }

  @Provides
  @Singleton
  internal fun provideCache(context: Context): Cache {
    var cache: Cache? = null
    try {
      cache = Cache(File(context.cacheDir, "http-cache"), (10 * 1024 * 1024).toLong()) // 10 MB
    } catch (e: Exception) {
      Timber.e(e, "Could not create Cache!")
    }

    return cache ?: throw IllegalStateException("Could not create Cache!")
  }

  @Provides
  internal fun provideEventListenerFactory(): HttpListenerFactory {
    return HttpListenerFactory
  }

}