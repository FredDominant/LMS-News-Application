package com.noblemajesty.newsapplication.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.noblemajesty.newsapplication.database.models.FoodNews
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.database.models.SportsNews

@Dao
interface NewsApplicationDAO {
    @Insert
    fun saveHomeNews(homeNews: HomeNews)

    @Insert
    fun saveSportsNews(sportsNews: SportsNews)

    @Insert
    fun saveFoodNews(foodNews: FoodNews)

    @Query("SELECT * FROM HomeNews" )
    fun getAllHomeNews() : LiveData<List<HomeNews>>

    @Query("SELECT * FROM SportsNews" )
    fun getAllSportsNews() : LiveData<List<SportsNews>>

    @Query("SELECT * FROM FoodNews" )
    fun getAllFoodNews() : LiveData<List<FoodNews>>
}