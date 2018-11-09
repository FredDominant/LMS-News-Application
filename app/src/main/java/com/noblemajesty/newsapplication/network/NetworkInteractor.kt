package com.noblemajesty.newsapplication.network

import com.noblemajesty.newsapplication.models.NYTimesResponse
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch

class NetworkInteractor {
    private var instance : NetworkInteractor? = null

    private lateinit var news: NYTimesResponse
    private lateinit var sports: NYTimesResponse
    private lateinit var food: NYTimesResponse
    private lateinit var movies: NYTimesResponse

    private var retrofitInstance = NYTimesRetrofitBuilder.getInstance()
            .createService(NYTimesService::class.java)

    fun getNetworkInteractor() : NetworkInteractor {
        return instance ?: NetworkInteractor()
    }

    fun fetchDataFromNYTimes(successCallback: (newsResponse: NYTimesResponse,
                                               sportsResponse: NYTimesResponse,
                                               foodResponse: NYTimesResponse) -> Unit,
                             errorCallback: (error: Exception) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val newsRequest = retrofitInstance.getNews()
            val sportsRequest = retrofitInstance.getSports()
            val foodRequest = retrofitInstance.getFood()
            try {
                news = newsRequest.await()
                sports = sportsRequest.await()
                food = foodRequest.await()
                successCallback(news, sports, food)
            } catch (error: Exception) {
                errorCallback(error)
            }
        }
    }
}