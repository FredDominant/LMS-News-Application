package com.noblemajesty.newsapplication.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.noblemajesty.newsapplication.utils.Secret.Companion.apiKey
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NYTimesRetrofitBuilder {
    companion object {
        const val BASE_URL = "https://api.nytimes.com/svc/topstories/v2/"
        private val retrofitBuilderInstance : NYTimesRetrofitBuilder? = null
        fun getInstance() : NYTimesRetrofitBuilder {
            return retrofitBuilderInstance ?: NYTimesRetrofitBuilder()
        }
    }

    private fun getNYTimesInterceptor() = NYTimesInterceptor(apiKey)

    private fun getHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
    }

    private fun getOkHttpClient() : OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(getNYTimesInterceptor())
            addInterceptor(getHttpLoggingInterceptor())
        }
        return okHttpClient.build()
    }

    private fun getBuilder() : Retrofit {
        val retrofitBuilder = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(getOkHttpClient())
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(CoroutineCallAdapterFactory())
        }
        return retrofitBuilder.build()
    }

    fun <T> createService(serviceClass: Class<T>): T {
        return getBuilder().create(serviceClass)
    }

}