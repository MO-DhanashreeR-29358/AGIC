package com.mosl.mobile.volunteer.appuser.util

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor() : Interceptor {
  override fun intercept(chain: Chain): Response {
    val original = chain.request()

    val builder = original.newBuilder()
    return chain.proceed(builder.build())
  }
}