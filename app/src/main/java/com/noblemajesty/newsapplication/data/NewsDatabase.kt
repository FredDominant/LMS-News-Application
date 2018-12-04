package com.noblemajesty.newsapplication.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import javax.xml.transform.Result

@Database(entities = [Result::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    var newsDatabaseInstance: NewsDatabase? = null

    fun getNewsDBInstance(context: Context): NewsDatabase {
        return newsDatabaseInstance ?: Room.databaseBuilder(context.applicationContext,
                this::class.java,
                "news-database").build()
    }

    abstract fun getDao(): NewsAppDao

    fun destroyInstance() { newsDatabaseInstance = null }
}