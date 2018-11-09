package com.noblemajesty.newsapplication.viewmodels

import android.arch.lifecycle.ViewModel
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.network.NetworkInteractor

class SplashActivityViewModel: ViewModel() {
    private var news: NYTimesResponse? = null
    private var sports: NYTimesResponse? = null
    private var food: NYTimesResponse? = null
    private var movies: NYTimesResponse? = null

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