package com.mosl.volunteer.coredata

import com.mosl.data.api.AGICApi
import dagger.Module
import dagger.Provides
import mosl.trading.annotation.InternalAPIClient
import mosl.trading.annotation.InternalAPIRetrofit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
  @Provides
  @Singleton
  internal fun provideMOSLApi(@InternalAPIClient internalOkClient: OkHttpClient,
      @InternalAPIRetrofit builder: Retrofit.Builder, apiBaseUrls: CommonBaseUrls): AGICApi {
    return builder.client(internalOkClient).baseUrl(apiBaseUrls.getMoslApiUrl())
        .build()
        .create(AGICApi::class.java)
  }
}