package com.noblemajesty.newsapplication.network

import com.noblemajesty.newsapplication.models.NYTimesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface NYTimesService {
    @GET ("home.json")
    fun getNews() : Observable<NYTimesResponse>

    @GET("food.json")
    fun getFood() : Observable<NYTimesResponse>

    @GET("sports.json")
    fun getSports() : Observable<NYTimesResponse>
}