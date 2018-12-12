package com.noblemajesty.newsapplication.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NewsApplicationDAO {
    @Insert
    fun saveNews(news: News)

    @Query("SELECT * FROM NEWS WHERE newsType = :newsType")
    fun getNews(newsType: String): LiveData<List<News>>
}