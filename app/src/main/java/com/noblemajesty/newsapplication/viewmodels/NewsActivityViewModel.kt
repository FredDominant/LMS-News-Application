package com.noblemajesty.newsapplication.viewmodels

import android.arch.lifecycle.ViewModel
import com.noblemajesty.newsapplication.models.getFood.NYTimesFoodResponse
import com.noblemajesty.newsapplication.models.getMovies.NYTimesMoviesResponse
import com.noblemajesty.newsapplication.models.getNews.NYTimesNewsResponse
import com.noblemajesty.newsapplication.models.getSports.NYTimesSportsResponse
import com.noblemajesty.newsapplication.network.NYTimesRetrofitBuilder
import com.noblemajesty.newsapplication.network.NYTimesService
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch
import java.lang.Exception

class NewsActivityViewModel: ViewModel() {

    private lateinit var news: NYTimesNewsResponse
    private lateinit var sports: NYTimesSportsResponse
    private lateinit var food: NYTimesFoodResponse
    private lateinit var movies: NYTimesMoviesResponse

    private var retrofitInstance = NYTimesRetrofitBuilder.getInstance()
            .createService(NYTimesService::class.java)

    fun fetchNews(success: (result: NYTimesNewsResponse) -> Unit,
                  error: (error: Exception) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val request = retrofitInstance.getNews()
                news = request.await()
                val response = request.await()
                news = response
                success(response)
            } catch (error: Exception) { error(error) }
        }
    }

    fun fetchSports(success: (result: NYTimesSportsResponse) -> Unit,
                    error: (error: Exception) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val request = retrofitInstance.getSports()
                sports = request.await()
                val response = request.await()
                success(response)
            } catch (error: Exception) { error(error) }
        }
    }

    fun fetchFood(success: (result: NYTimesFoodResponse) -> Unit,
                  error: (error: Exception) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val request = retrofitInstance.getFood()
                val response = request.await()
                food = response
                success(response)
            } catch (error: Exception) { error(error) }
        }
    }

    fun fetchMovies(success: (result: NYTimesMoviesResponse) -> Unit,
                    error: (error: Exception) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val request = retrofitInstance.getMovies()
                val response = request.await()
                movies = response
                success(response)
            } catch (error: Exception) { error(error) }
        }
    }
}