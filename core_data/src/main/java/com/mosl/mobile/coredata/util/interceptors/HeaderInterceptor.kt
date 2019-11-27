package com.mosl.mobile.coredata.util.interceptors

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Content-Type", "application/json; charset=UTF-8")
            .build()
        return chain.proceed(request)
    }
}