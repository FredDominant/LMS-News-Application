package com.noblemajesty.newsapplication.viewmodels

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.noblemajesty.newsapplication.models.getFood.NYTimesFoodResponse
import com.noblemajesty.newsapplication.models.getMovies.NYTimesMoviesResponse
import com.noblemajesty.newsapplication.models.getNews.NYTimesNewsResponse
import com.noblemajesty.newsapplication.models.getSports.NYTimesSportsResponse
import com.noblemajesty.newsapplication.network.NetworkInteractor

class SplashActivityViewModel: ViewModel() {
    private var news: NYTimesNewsResponse? = null
    private var sports: NYTimesSportsResponse? = null
    private var food: NYTimesFoodResponse? = null
    private var movies: NYTimesMoviesResponse? = null

    private var error : Exception? = null

    fun getData() : HashMap<String, Any> {
        val reponseHashMap = HashMap<String, Any>()
        val data = NetworkInteractor()
                .getNetworkInteractor()
                .fetchDataFromNYTimes({ news, sports, food, movies ->
                    reponseHashMap["NEWS"] = news
                    reponseHashMap["SPORTS"] = sports
                    reponseHashMap["FOOD"] = food
                    reponseHashMap["MOVIES"] = movies
                }, { it -> reponseHashMap["ERROR"] = it})
        return reponseHashMap
    }
}