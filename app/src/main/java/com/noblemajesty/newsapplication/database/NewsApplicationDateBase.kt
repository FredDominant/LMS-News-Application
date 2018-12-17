package com.noblemajesty.newsapplication.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NewsApplicationDataBase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val TABLE_NAME = "News"
        const val DB_NAME = "newsApplicationDatabase.db"
        const val DB_VERSION = 1
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_ABSTRACT = "abstract"
        const val COLUMN_BYLINE = "byline"
        const val COLUMN_PUBLISHED_DATE = "publishedDate"
        const val COLUMN_IMAGE = "image"
        const val COLUMN_NEWS_TYPE = "newsType"
        val TABLE_ROWS = arrayOf(COLUMN_ID, COLUMN_TITLE, COLUMN_ABSTRACT,
                COLUMN_BYLINE, COLUMN_PUBLISHED_DATE, COLUMN_IMAGE, COLUMN_NEWS_TYPE )


    }
    private val CREATE_NEWS_TABLE_QUERY =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COLUMN_TITLE TEXT, " +
            "$COLUMN_ABSTRACT TEXT, " +
            "$COLUMN_BYLINE TEXT, " +
            "$COLUMN_PUBLISHED_DATE TEXT, " +
            "$COLUMN_IMAGE TEXT, " +
            "$COLUMN_NEWS_TYPE TEXT);"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_NEWS_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}