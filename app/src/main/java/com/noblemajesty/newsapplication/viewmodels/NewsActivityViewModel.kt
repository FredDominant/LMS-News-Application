package com.noblemajesty.newsapplication.viewmodels

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.noblemajesty.newsapplication.R.id.food
import com.noblemajesty.newsapplication.R.id.sports
import com.noblemajesty.newsapplication.database.models.FoodNews
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.database.models.SportsNews
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.models.Result
import com.noblemajesty.newsapplication.network.NYTimesRetrofitBuilder
import com.noblemajesty.newsapplication.network.NYTimesService
import io.realm.Realm
import io.realm.RealmObject
import io.realm.kotlin.createObject
import kotlinx.coroutines.experimental.Deferred
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

    companion object {
        const val NEWS = "news"
        const val SPORTS = "sports"
        const val FOOD = "food"
    }

    private var retrofitInstance = NYTimesRetrofitBuilder.getInstance()
            .createService(NYTimesService::class.java)

    fun getDataFromAPI(newsType: String,
                       success: (result: NYTimesResponse) -> Unit,
                       error: (error: Exception) -> Unit) {
        var request: Deferred<NYTimesResponse>
        var response: NYTimesResponse
        GlobalScope.launch (Dispatchers.Main){
            try {
                when (newsType) {
                    NEWS -> {
                        request = retrofitInstance.getNews()
                        response = request.await()
                        news = response
                        success(response)
                        for (item in response.results) saveItemToRealmDB(HomeNews::class.java, item)
                    }
                    SPORTS -> {
                        request = retrofitInstance.getSports()
                        response = request.await()
                        sports = response
                        success(response)
                        for (item in response.results) saveItemToRealmDB(SportsNews::class.java, item)
                    }
                    FOOD -> {
                        request = retrofitInstance.getFood()
                        response = request.await()
                        food = response
                        success(response)
                        for (item in response.results) saveItemToRealmDB(FoodNews::class.java, item)
                    }
                }

            } catch (error: Exception) { error(error) }
        }
    }

    private fun <T: RealmObject>saveItemToRealmDB(objectClass: Class<T>, item: Result) {
        db.executeTransactionAsync({ realmDB ->
            val generatedObject = realmDB.createObject(objectClass)
            when(generatedObject) {
                is HomeNews -> {
                        (generatedObject as HomeNews).apply {
                        abstract = item.abstract
                        title = item.title
                        byline = item.byline
                        publishedDate = item.published_date
                        image = if (item.multimedia.isNotEmpty()) {
                            item.multimedia[3].url
                        } else { null }
                    }
                }
                is FoodNews -> {
                    (generatedObject as FoodNews).apply {
                        abstract = item.abstract
                        title = item.title
                        byline = item.byline
                        publishedDate = item.published_date
                        image = if (item.multimedia.isNotEmpty()) {
                            item.multimedia[3].url
                        } else { null }
                    }
                }
                is SportsNews -> {
                    (generatedObject as SportsNews).apply {
                        abstract = item.abstract
                        title = item.title
                        byline = item.byline
                        publishedDate = item.published_date
                        image = if (item.multimedia.isNotEmpty()) {
                            item.multimedia[3].url
                        } else { null }
                    }
                }
            }
        }, { Log.e("News Realm Success", "saved") }, { Log.e("News Realm Error", "${it.message}") })
    }
}