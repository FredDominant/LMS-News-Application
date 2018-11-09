package com.noblemajesty.newsapplication.network

import com.noblemajesty.newsapplication.models.NYTimesResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface NYTimesService {
    @GET ("home.json")
    fun getNews() : Deferred<NYTimesResponse>

    @GET("food.json")
    fun getFood() : Deferred<NYTimesResponse>

    @GET("sports.json")
    fun getSports() : Deferred<NYTimesResponse>
}