package com.mosl.mobile.volunteer.coredata

import android.content.Context
import com.mosl.data.api.AGICApi
import com.mosl.debugview.core.annotation.IsMockMode
import com.mosl.mobile.volunteer.mock_data.MockMoslApiServiceImpl
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
      context: Context): AGICApi {
    return MockMoslApiServiceImpl(mockRetrofit, context)
  }

  @Provides
  @Singleton
  internal fun provideMoslApi(@InternalAPIClient internalOkClient: OkHttpClient,
                              @InternalAPIRetrofit builder: Retrofit.Builder, @IsMockMode isMockMode: Boolean,
                              @Named("MockMoslApi") mockMoslApi: AGICApi, apiBaseUrls: CommonBaseUrls
  ): AGICApi {
    return if (isMockMode) {
      mockMoslApi
    } else {
      builder.client(internalOkClient).baseUrl(apiBaseUrls.getMoslApiUrl())
          .build()
          .create(AGICApi::class.java)
    }
  }
}