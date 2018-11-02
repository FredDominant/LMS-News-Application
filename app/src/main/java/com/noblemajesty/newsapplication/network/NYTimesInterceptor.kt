package com.noblemajesty.newsapplication.network

import okhttp3.Interceptor
import okhttp3.Response

class NYTimesInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url()
                .newBuilder()
                .addQueryParameter("api-key", apiKey)
                .build()

        val newRequestBody = request.newBuilder()
                .url(url)
                .build()
        return chain.proceed(newRequestBody)
    }
}