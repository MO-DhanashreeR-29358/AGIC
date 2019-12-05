package com.mosl.mobile.coredata.util.interceptors

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by lalkishore on 2/28/2017.
 */

class CacheInterceptor @Inject internal constructor() : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val cacheFor = request.header(CACHE_FOR)
    if (cacheFor != null) {
      val response = chain.proceed(request.newBuilder().removeHeader(CACHE_FOR).build())

      // re-write response header to force use of cache
      val cacheControl = CacheControl.Builder()
        .maxAge(Integer.parseInt(cacheFor), TimeUnit.MINUTES)
        .build()

      return response.newBuilder()
        .removeHeader(PRAGMA)
        .header(CACHE_CONTROL, cacheControl.toString())
        .build()
    }
    return chain.proceed(request)
  }

  companion object {
    private const val CACHE_FOR = "Cache-For"
    private const val CACHE_CONTROL = "Cache-Control"
    private const val PRAGMA = "Pragma"
  }
}
