package com.mosl.volunteer.coredata.util.interceptors

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
            .header("Content-Type", "application/json;charset=utf-8")
        val response = chain.proceed(builder.build())
        return response
    }
}