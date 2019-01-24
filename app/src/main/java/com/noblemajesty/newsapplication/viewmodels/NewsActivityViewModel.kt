package com.noblemajesty.newsapplication.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.ContentResolver
import android.content.ContentValues
import android.net.Uri
import com.noblemajesty.newsapplication.database.News
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase.Companion.COLUMN_ABSTRACT
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase.Companion.COLUMN_BYLINE
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase.Companion.COLUMN_IMAGE
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase.Companion.COLUMN_NEWS_TYPE
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase.Companion.COLUMN_PUBLISHED_DATE
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase.Companion.COLUMN_TITLE
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase.Companion.COLUMN_ID
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.models.Result
import com.noblemajesty.newsapplication.network.NYTimesRetrofitBuilder
import com.noblemajesty.newsapplication.network.NYTimesService
import com.noblemajesty.newsapplication.providers.NewsApplicationProvider.Companion.CONTENT_URI
import com.noblemajesty.newsapplication.utils.Constants.FOOD
import com.noblemajesty.newsapplication.utils.Constants.HOME_NEWS
import com.noblemajesty.newsapplication.utils.Constants.SPORTS
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

open class NewsActivityViewModel: ViewModel() {
    var disposable: Disposable? = null
    private var retrofitInstance = NYTimesRetrofitBuilder.getInstance()
            .createService(NYTimesService::class.java)
    val newsArray = MutableLiveData<ArrayList<News>>()
    var contentResolver: ContentResolver? = null

    fun getNews(newsType: String): Observable<NYTimesResponse>? {
        var observable: Observable<NYTimesResponse>? = null
        when (newsType) {
            HOME_NEWS -> { observable = retrofitInstance.getNews() }
            FOOD -> { observable = retrofitInstance.getFood() }
            SPORTS -> { observable = retrofitInstance.getSports() }
        }
        return observable
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchNewsFromDataBase(newsType: String) {
        disposable = Flowable.fromCallable<ArrayList<News>> {
            val allNews = ArrayList<News>()
            val cursor = contentResolver?.query(Uri.parse("$CONTENT_URI/$newsType"),
                    NewsApplicationDataBase.TABLE_ROWS, null, null, null)

            cursor?.let {
                while (it.moveToNext()) {
                    val news = News().apply {
                        id = it.getInt(it.getColumnIndex(COLUMN_ID))
                        title = it.getString(it.getColumnIndex(COLUMN_TITLE))
                        abstract = it.getString(it.getColumnIndex(COLUMN_ABSTRACT))
                        byline = it.getString(it.getColumnIndex(COLUMN_BYLINE))
                        publishedDate = it.getString(it.getColumnIndex(COLUMN_PUBLISHED_DATE))
                        image = it.getString(it.getColumnIndex(COLUMN_IMAGE))
                    }
                    allNews.add(news)
                }
                it.close()
            }
            allNews
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    newsArray.value = it }
                .flatMap {
                    getNews(newsType)?.toFlowable(BackpressureStrategy.BUFFER)
                }
                .doOnNext {
//                    saveNewsToDB(it.results, newsType)
                    saveNewsToDBWithContentProvider(it.results, newsType)
                }
                .map {
                    it.results.map { it.toNews() }
//                    it.results.map { it.toContentValue() }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    newsArray.value = ArrayList(it)
                }, {})
    }

    fun Result.toNews(): News {
        val stuff = this
        return News().apply {
            title = stuff.title
            byline = stuff.byline
            abstract = stuff.abstract
            publishedDate = stuff.published_date
            image = if (stuff.multimedia.isNotEmpty()) stuff.multimedia[3].url else null
        }
    }

    fun clearDisposable() = disposable?.dispose()

    fun saveWithContentProvider(uri: Uri, contentValues: ContentValues) {
        try {
            val uri = contentResolver?.insert(uri, contentValues)
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

    fun saveNewsToDBWithContentProvider(data: List<Result>, newsType: String) {
        data.map {
            val contentValues = ContentValues().apply {
                put(COLUMN_TITLE, it.title)
                put(COLUMN_ABSTRACT, it.abstract)
                put(COLUMN_BYLINE, it.byline)
                put(COLUMN_PUBLISHED_DATE, it.published_date)
                put(COLUMN_IMAGE, if (it.multimedia.isNotEmpty()) { it.multimedia[3].url } else { null })
                put(COLUMN_NEWS_TYPE, newsType)
            }
            saveWithContentProvider( Uri.parse("$CONTENT_URI/$newsType"), contentValues)
        }
    }
}

