package com.noblemajesty.newsapplication.viewmodels

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.noblemajesty.newsapplication.R.id.food
import com.noblemajesty.newsapplication.R.id.sports
import com.noblemajesty.newsapplication.database.models.FoodNews
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.database.models.SportsNews
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.network.NYTimesRetrofitBuilder
import com.noblemajesty.newsapplication.network.NYTimesService
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import java.util.*

class NewsActivityViewModel: ViewModel() {
    var news: NYTimesResponse? = null
    var sports: NYTimesResponse? = null
    var food: NYTimesResponse? = null
    var show = true
    private val db = Realm.getDefaultInstance()

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
                for (item in response.results) {
                    db.executeTransactionAsync({
                        val homeNews = it.createObject(HomeNews::class.java)
                        homeNews.apply {
                            abstract = item.abstract
                            title = item.title
                            byline = item.byline
                            publishedDate = item.published_date
                            image = if (item.multimedia.isNotEmpty()) {
                                item.multimedia[3].url
                            } else { null }
                        }
                    }, { Log.e("News Realm Success", "saved") },
                        { Log.e("News Realm Error", "${it.message}") })
                }
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
                for (item in response.results) {
                    db.executeTransactionAsync({
                        val homeNews = it.createObject(SportsNews::class.java)
                        homeNews.apply {
                            abstract = item.abstract
                            title = item.title
                            byline = item.byline
                            publishedDate = item.published_date
                            image = if (item.multimedia.isNotEmpty()) {
                                item.multimedia[3].url
                            } else { null }
                        }
                    }, {
                        Log.e("News Realm Success", "saved")
                    }, {
                        Log.e("News Realm Error", "${it.message}")
                    })
                }
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
                for (item in response.results) {
                    db.executeTransactionAsync({
                        val homeNews = it.createObject(FoodNews::class.java)
                        homeNews.apply {
                            abstract = item.abstract
                            title = item.title
                            byline = item.byline
                            publishedDate = item.published_date
                            image = if (item.multimedia.isNotEmpty()) {
                                item.multimedia[3].url
                            } else { null }
                        }
                    }, {
                        Log.e("News Realm Success", "saved")
                    }, {
                        Log.e("News Realm Error", "${it.message}")
                    })
                }

            } catch (error: Exception) { error(error) }
        }
    }

//    private fun <T : RealmModel> saveDataToRealmDB(objectClass: T, apiResponse: Result) {
//        var stuff: T? = null
//        when (stuff) {
//            is HomeNews -> { stuff = T as? HomeNews }
//        }
//
//    }
//
//    private fun doStuff() {
//
//    }
//    db.executeTransactionAsync { realmDB ->
//        val data = realmDB.createObject(objectClass::class.java, UUID.randomUUID())
//        data.apply {
//            this as HomeNews
//            abstract = apiResponse.abstract
//            title = apiResponse.title
//            byline = apiResponse.byline
//            publishedDate = apiResponse.published_date
//            image = if (apiResponse.multimedia.isNotEmpty()) { apiResponse.multimedia[3].url }
//            else { null }
//        }
//    }

}