package com.mosl.mobile.data

import android.content.Context
import com.mosl.data.api.MOSLApi
import com.mosl.debugview.core.annotation.IsMockMode
import com.mosl.mobile.mock_data.MockMoslApiServiceImpl
import dagger.Module
import dagger.Provides
import mosl.trading.annotation.InternalAPIClient
import mosl.trading.annotation.InternalAPIRetrofit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.mock.MockRetrofit

@Module
class DebugApiModule {

  @Provides
  @Singleton
  @Named("MockMoslApi")
  internal fun provideMockAmcApiService(mockRetrofit: MockRetrofit,
      context: Context): MOSLApi {
    return MockMoslApiServiceImpl(mockRetrofit, context)
  }

  @Provides
  @Singleton
  internal fun provideMoslApi(@InternalAPIClient internalOkClient: OkHttpClient,
      @InternalAPIRetrofit builder: Retrofit.Builder, @IsMockMode isMockMode: Boolean,
      @Named("MockMoslApi") mockMoslApi: MOSLApi, apiBaseUrls: CommonBaseUrls): MOSLApi {
    return if (isMockMode) {
      mockMoslApi
    } else {
      builder.client(internalOkClient).baseUrl(apiBaseUrls.getMoslApiUrl())
          .build()
          .create(MOSLApi::class.java)
    }
  }
}