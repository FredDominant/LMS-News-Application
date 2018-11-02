package com.noblemajesty.newsapplication.network

import com.noblemajesty.newsapplication.models.getFood.NYTimesFoodResponse
import com.noblemajesty.newsapplication.models.getMovies.NYTimesMoviesResponse
import com.noblemajesty.newsapplication.models.getNews.NYTimesNewsResponse
import com.noblemajesty.newsapplication.models.getSports.NYTimesSportsResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface NYTimesService {
    @GET ("home.json")
    fun getNews() : Deferred<NYTimesNewsResponse>

    @GET("food.json")
    fun getFood() : Deferred<NYTimesFoodResponse>

    @GET("sports.json")
    fun getSports() : Deferred<NYTimesSportsResponse>

    @GET("movies.json")
    fun getMovies() : Deferred<NYTimesMoviesResponse>
}