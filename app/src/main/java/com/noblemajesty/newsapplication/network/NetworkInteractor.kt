package com.noblemajesty.newsapplication.network

import com.noblemajesty.newsapplication.models.getFood.NYTimesFoodResponse
import com.noblemajesty.newsapplication.models.getMovies.NYTimesMoviesResponse
import com.noblemajesty.newsapplication.models.getNews.NYTimesNewsResponse
import com.noblemajesty.newsapplication.models.getSports.NYTimesSportsResponse
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch

class NetworkInteractor {
    private var instance : NetworkInteractor? = null

    private lateinit var news: NYTimesNewsResponse
    private lateinit var sports: NYTimesSportsResponse
    private lateinit var food: NYTimesFoodResponse
    private lateinit var movies: NYTimesMoviesResponse

    private var retrofitInstance = NYTimesRetrofitBuilder.getInstance()
            .createService(NYTimesService::class.java)

    fun getNetworkInteractor() : NetworkInteractor {
        return instance ?: NetworkInteractor()
    }

    fun fetchDataFromNYTimes(successCallback: (newsResponse: NYTimesNewsResponse,
                                               sportsResponse: NYTimesSportsResponse,
                                               foodResponse: NYTimesFoodResponse,
                                               moviesResponse: NYTimesMoviesResponse) -> Unit,
                             errorCallback: (error: Exception) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val newsRequest = retrofitInstance.getNews()
            val sportsRequest = retrofitInstance.getSports()
            val foodRequest = retrofitInstance.getFood()
            val moviesRequest = retrofitInstance.getMovies()
            try {
                news = newsRequest.await()
                sports = sportsRequest.await()
                food = foodRequest.await()
                movies = moviesRequest.await()
                successCallback(news, sports, food, movies)
            } catch (error: Exception) {
                errorCallback(error)
            }
        }
    }
}