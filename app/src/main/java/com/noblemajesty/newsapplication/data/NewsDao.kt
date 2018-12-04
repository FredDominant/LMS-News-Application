package com.noblemajesty.newsapplication.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NewsAppDao {
    @Dao
    interface newsDao {
        @Query("SELECT * FROM news")
        fun getAllNews(): LiveData<List<NewsColumn>>

        @Insert
        fun addNews(vararg news: NewsColumn)
    }

    @Dao
    interface sportsDao {
        @Query("SELECT * FROM sports")
        fun getAllSports(): LiveData<List<SportsColumn>>

        @Insert
        fun addSports(vararg sports: SportsColumn)
    }

    @Dao
    interface foodDao {
        @Query("SELECT * FROM food")
        fun getAllFood(): LiveData<List<FoodColumn>>

        @Insert
        fun addFood(vararg food: FoodColumn)
    }
}