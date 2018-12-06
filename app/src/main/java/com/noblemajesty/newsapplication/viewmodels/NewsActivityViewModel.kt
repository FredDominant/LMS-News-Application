package com.noblemajesty.newsapplication.viewmodels

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.noblemajesty.newsapplication.database.models.FoodNews
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.database.models.SportsNews
import com.noblemajesty.newsapplication.models.NYTimesResponse
import com.noblemajesty.newsapplication.models.Result
import com.noblemajesty.newsapplication.network.NYTimesRetrofitBuilder
import com.noblemajesty.newsapplication.network.NYTimesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmAsyncTask
import io.realm.RealmModel
import io.realm.RealmObject

class NewsActivityViewModel: ViewModel() {
    var news: NYTimesResponse? = null
    var sports: NYTimesResponse? = null
    var food: NYTimesResponse? = null
    var show = true
    private var disposable: Disposable? = null
    private var realmAsyncTask: RealmAsyncTask? = null

    private val db = Realm.getDefaultInstance()

    companion object {
        const val NEWS = "news"
        const val SPORTS = "sports"
        const val FOOD = "food"
    }

    private var retrofitInstance = NYTimesRetrofitBuilder.getInstance()
            .createService(NYTimesService::class.java)

    fun getDataFromAPI(newsType: String, success: ((result: NYTimesResponse) -> Unit)? = null,
                       errorCallback: ((errorMessage: String) -> Unit)? = null) {
        when(newsType) {
            NEWS -> {
                disposable = retrofitInstance.getNews()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    news = it
                                    removePreviousDataFromRealmDB(HomeNews::class.java)
                                    for (item in it.results) saveItemToRealmDB(HomeNews::class.java, item)
                                    success?.invoke(it)
                                },
                                { it -> it.message?.let{ errorCallback?.invoke(it) } })
            }
            SPORTS -> {
                disposable = retrofitInstance.getSports()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    sports = it
                                    removePreviousDataFromRealmDB(SportsNews::class.java)
                                    for (item in it.results) saveItemToRealmDB(SportsNews::class.java, item)
                                    success?.invoke(it)
                                },
                                { it -> it.message?.let{ errorCallback?.invoke(it) } }, {})
            }
            FOOD -> {
                disposable = retrofitInstance.getFood()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    food = it
                                    removePreviousDataFromRealmDB(FoodNews::class.java)
                                    for (item in it.results) saveItemToRealmDB(FoodNews::class.java, item)
                                    success?.invoke(it)
                                },
                                { it -> it.message?.let{ errorCallback?.invoke(it) } }, {})
            }
        }
    }

    private fun <T: RealmObject>saveItemToRealmDB(objectClass: Class<T>, item: Result) {
        realmAsyncTask = db.executeTransactionAsync({ realmDB ->
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
            closeDB()
        }, { Log.e("News Realm Success", "saved") }, { Log.e("News Realm Error", "${it.message}") })
    }

    private fun <T : RealmModel?> removePreviousDataFromRealmDB(clazz: Class<T>) {
        val result = db.where(clazz).findAll()
        db.beginTransaction()
        val resp = result.deleteAllFromRealm()
        db.commitTransaction()
        Log.e("delete success?", "$resp")
    }

    fun clearDisposable() = disposable?.dispose()

    private fun closeDB() = if (db.isClosed) { } else db.close()

    fun clearAsyncTask() {
        realmAsyncTask?.let {
            if (!it.isCancelled) it.cancel()
        }
    }

}