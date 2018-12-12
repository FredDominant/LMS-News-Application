package com.noblemajesty.newsapplication.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase
import com.noblemajesty.newsapplication.database.News
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.network.NYTimesRetrofitBuilder
import com.noblemajesty.newsapplication.network.NYTimesService
import com.noblemajesty.newsapplication.utils.Constants.FOOD
import com.noblemajesty.newsapplication.utils.Constants.HOME_NEWS
import com.noblemajesty.newsapplication.utils.Constants.SPORTS
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NewsActivityViewModel: ViewModel() {
    var database: NewsApplicationDataBase? = null
    private var disposable: Disposable? = null
    private var retrofitInstance = NYTimesRetrofitBuilder.getInstance()
            .createService(NYTimesService::class.java)

    fun getNews(newsType: String): LiveData<List<News>>? {
        var observable: Observable<NYTimesResponse>? = null
        when (newsType) {
            HOME_NEWS -> { observable = retrofitInstance.getNews() }
            FOOD -> { observable = retrofitInstance.getFood() }
            SPORTS -> { observable = retrofitInstance.getSports() }
        }
        disposable = observable
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        { apiResponse ->
                            apiResponse.results.map { news ->
                                val multimedia = news.multimedia
                                val image = if (multimedia.isNotEmpty()) { multimedia[3].url } else { null }
                                val newsItem = News(title = news.title,
                                        abstract = news.abstract,
                                        byline = news.byline,
                                        publishedDate = news.published_date,
                                        image = image,
                                        newsType = newsType)
                                Log.e("from API", "$newsItem")
                                saveNewsToDB(newsItem)
                            }

                        },
                        {
                            Log.e("Error from inside API", "$it")
                            it.printStackTrace()
                        }
                )
        return database?.getDao()?.getNews(newsType)
    }

    private fun saveNewsToDB(item: News) = database?.getDao()?.saveNews(item)

    fun clearDisposable() = disposable?.dispose()
}