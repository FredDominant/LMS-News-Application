package com.noblemajesty.newsapplication.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase
import com.noblemajesty.newsapplication.database.models.FoodNews
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.database.models.SportsNews
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.network.NYTimesRetrofitBuilder
import com.noblemajesty.newsapplication.network.NYTimesService
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewsActivityViewModel: ViewModel() {
    var news: NYTimesResponse? = null
    var sports: NYTimesResponse? = null
    var food: NYTimesResponse? = null
    var show = true
    private var retrofitInstance = NYTimesRetrofitBuilder.getInstance()
            .createService(NYTimesService::class.java)
    private var disposable: Disposable? = null
    var database: NewsApplicationDataBase? = null

    companion object {
        const val NEWS = "news"
        const val SPORTS = "sports"
        const val FOOD = "food"
    }

    fun getHomeNews(): LiveData<List<HomeNews>>? {
        Log.e("getting from News API", "I am hereeeeeeeeeee")
        disposable = retrofitInstance.getNews()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { apiResponse ->
                            apiResponse.results.map { news ->
                                val multimedia = news.multimedia
                                val image = if (multimedia.isNotEmpty()) { multimedia[3].url } else { null }
                                val homeNews = HomeNews( title = news.title,
                                        abstract = news.abstract,
                                        byline = news.byline,
                                        publishedDate = news.published_date,
                                        image = image)
                                Log.e("from API", "$homeNews")
                                saveNewsToDB(homeNews)
                            }
                        },
                        {
                            Log.e("from inside API", "$it")
                            it.printStackTrace()
                        })
        return database?.getDao()?.getAllHomeNews()
    }

    fun getSportsNews(): LiveData<List<SportsNews>>? {
        Log.e("getting from API", "I am hereeeeeeeeeee")
        disposable = retrofitInstance.getSports()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            for (item in it.results) {
                                val image = if (item.multimedia.isNotEmpty()) {
                                    item.multimedia[3].url }
                                else { null }
                                val sportsNews = SportsNews( title = item.title,
                                        abstract = item.abstract,
                                        byline = item.byline,
                                        publishedDate = item.published_date,
                                        image = image)
                                Log.e("from API", "$sportsNews")
//                                saveNewsToDB(SportsNews::class.java, sportsNews)
                            }
                        },
                        {
                            Log.e("from inside API", "$it")
                            it.printStackTrace()
                        })
        return database?.getDao()?.getAllSportsNews()
    }

    private fun saveNewsToDB(item: HomeNews ) = database?.getDao()?.saveHomeNews(item)

//    private fun <T> saveNewsToDB(clazz: Class<T>, item: T ) {
//        when (clazz) {
//            is HomeNews -> { database?.getDao()?.saveHomeNews(item as HomeNews) }
//            is SportsNews -> { database?.getDao()?.saveSportsNews(item as SportsNews) }
//            is FoodNews -> { database?.getDao()?.saveFoodNews(item as FoodNews) }
//        }
//
//    }

    fun clearDisposable() = disposable?.dispose()

}