package com.noblemajesty.newsapplication.repository

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.util.Log
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.network.NYTimesService
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

class HomeNewsRepository (private val apiService: NYTimesService, private val database: NewsApplicationDataBase) {


//    private val observable = Observable.concatArray(
////            getHomeNewsFromDB(),
//            getHomeNewsFromAPI()
//    )
//
//    fun getHomeNews(): Observable<List<HomeNews>> {
//        Log.e("From Repository", "called from here....")
//       return observable
//    }

    fun getHomeNewsFromDB(): LiveData<List<HomeNews>> {

        return database.getDao().getAllHomeNews()
    }

//    private fun getHomeNewsFromDB(): Observable<List<HomeNews>> {
//        val news = database.where(HomeNews::class.java).findAll()
//        Log.e("getting from DB", "$news")
//        val newsList = arrayListOf<HomeNews>()
//        for (item in news) {
//            Log.e("from DB", "$item")
//            newsList.add(item)
//        }
//        return Observable.fromArray(newsList)
//    }okay

    @SuppressLint("CheckResult")
    fun getHomeNews(): LiveData<List<HomeNews>> {
        Log.e("getting from APIB", "I am hereeeeeeeeeee")
        val newsList = arrayListOf<HomeNews>()
        apiService.getNews()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            for (item in it.results) {
                                val image = if (item.multimedia.isNotEmpty()) { item.multimedia[3].url } else { null }
                                val homeNews = HomeNews( title = item.title, abstract = item.abstract, byline = item.byline, publishedDate = item.published_date, image = image)
                                Log.e("from API", "$homeNews")
                                saveHomeNewsToDB(homeNews)
                                newsList.add(homeNews)
                            }
                },
                        {
                            Log.e("from inside API", "$it")
                            it.printStackTrace()
                        })
        return database.getDao().getAllHomeNews()
    }

//    @SuppressLint("CheckResult")
//    fun saveHomeNewsToDB(item: HomeNews) {
//                database.executeTransactionAsync { db ->
//                    db.insertOrUpdate(item)
//                }
//        Log.e("saved to DB", "$item")
//
//    }

    private fun saveHomeNewsToDB(item: HomeNews) {
        database.getDao().saveHomeNews(item)
    }
}