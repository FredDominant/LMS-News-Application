package com.noblemajesty.newsapplication.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.noblemajesty.newsapplication.database.models.FoodNews
import com.noblemajesty.newsapplication.database.models.HomeNews
import com.noblemajesty.newsapplication.database.models.SportsNews

@Database(entities = [HomeNews::class, FoodNews::class, SportsNews::class], version = 1)
abstract class NewsApplicationDataBase : RoomDatabase() {

    abstract fun getDao(): NewsApplicationDAO

    companion object {
        private var databaseInstance: NewsApplicationDataBase? = null

        fun getDatabaseInstance(context: Context): NewsApplicationDataBase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(context.applicationContext,
                        NewsApplicationDataBase::class.java, "newsApplicationDatabase")
                        .allowMainThreadQueries()
                        .build()
            }
            return databaseInstance as NewsApplicationDataBase
        }

        fun destroyInstance() {
            databaseInstance?.let { databaseInstance = null }
        }
    }
}