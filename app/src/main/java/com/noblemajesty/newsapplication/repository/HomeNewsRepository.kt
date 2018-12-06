package com.noblemajesty.newsapplication.repository

import android.annotation.SuppressLint
import android.util.Log
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.network.NYTimesService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

class HomeNewsRepository (private val apiService: NYTimesService, private val database: Realm) {

    private val observable = Observable.concatArray(
            getHomeNewsFromDB(),
            getHomeNewsFromAPI()
    )

    fun getHomeNews(): Observable<List<HomeNews>> {
        Log.e("From Repository", "called from here....")
       return observable
    }

    private fun getHomeNewsFromDB(): Observable<List<HomeNews>> {
        val news = database.where(HomeNews::class.java).findAll()
        Log.e("getting from DB", "$news")
        val newsList = arrayListOf<HomeNews>()
        for (item in news) {
            Log.e("from DB", "$item")
            newsList.add(item)
        }
        return Observable.fromArray(newsList)
    }

    @SuppressLint("CheckResult")
    fun getHomeNewsFromAPI(): Observable<List<HomeNews>> {
        Log.e("getting from APIB", "I am hereeeeeeeeeee")
        val newsList = arrayListOf<HomeNews>()
        apiService.getNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            for (item in it.results) {
                                val image = if (item.multimedia.isNotEmpty()) { item.multimedia[3].url } else { null }
                                val homeNews = HomeNews(item.title, item.abstract, item.byline, item.published_date, image)
                                Log.e("from API", "$homeNews")
                                saveHomeNewsToDB(homeNews)
                                newsList.add(homeNews)
                            }
                },
                        {
                            Log.e("from inside API", "$it")
                            it.printStackTrace()
                        })
        return Observable.fromArray(newsList)
    }

    @SuppressLint("CheckResult")
    fun saveHomeNewsToDB(item: HomeNews) {
                database.executeTransactionAsync { db ->
                    db.insertOrUpdate(item)
                }
        Log.e("saved to DB", "$item")

    }
}