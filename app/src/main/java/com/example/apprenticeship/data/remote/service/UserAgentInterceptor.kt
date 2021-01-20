package com.example.apprenticeship.data.remote.service

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.userAgent

class UserAgentInterceptor :Interceptor{

    companion object{
        private const val USER_AGENT = "user-Agent"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithUserAgent = originalRequest.newBuilder()
            .header(USER_AGENT, userAgent)
            .build()
        return chain.proceed(requestWithUserAgent)
    }
}