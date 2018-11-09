package com.noblemajesty.newsapplication.viewmodels

import android.arch.lifecycle.ViewModel
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.network.NYTimesRetrofitBuilder
import com.noblemajesty.newsapplication.network.NYTimesService
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch

class NewsActivityViewModel: ViewModel() {
    var news: NYTimesResponse? = null
    var sports: NYTimesResponse? = null
    var food: NYTimesResponse? = null
    var show = true
    private var retrofitInstance = NYTimesRetrofitBuilder.getInstance()
            .createService(NYTimesService::class.java)

    fun fetchNews(success: (result: NYTimesResponse) -> Unit,
                  error: (error: Exception) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val request = retrofitInstance.getNews()
                news = request.await()
                val response = request.await()
                news = response
                success(response)
            } catch (error: Exception) { error(error) }
        }
    }

    fun fetchSports(success: (result: NYTimesResponse) -> Unit,
                    error: (error: Exception) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val request = retrofitInstance.getSports()
                sports = request.await()
                val response = request.await()
                success(response)
            } catch (error: Exception) { error(error) }
        }
    }

    fun fetchFood(success: (result: NYTimesResponse) -> Unit,
                  error: (error: Exception) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val request = retrofitInstance.getFood()
                val response = request.await()
                food = response
                success(response)
            } catch (error: Exception) { error(error) }
        }
    }

}