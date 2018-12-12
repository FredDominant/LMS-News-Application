package com.noblemajesty.newsapplication.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.noblemajesty.newsapplication.utils.Constants.DB_NAME

@Database(entities = [News::class], version = 1)
abstract class NewsApplicationDataBase : RoomDatabase() {

    abstract fun getDao(): NewsApplicationDAO

    companion object {
        private var databaseInstance: NewsApplicationDataBase? = null

        fun getDatabaseInstance(context: Context): NewsApplicationDataBase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(context.applicationContext,
                        NewsApplicationDataBase::class.java, DB_NAME)
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