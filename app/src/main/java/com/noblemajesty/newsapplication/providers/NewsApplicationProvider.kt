package com.noblemajesty.newsapplication.providers

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase.Companion.TABLE_NAME
import java.lang.IllegalArgumentException

class NewsApplicationProvider: ContentProvider() {

    private lateinit var database: NewsApplicationDataBase

    companion object {
        private const val CONTENT_AUTHORITY = "com.noblemajesty.newsapplication.providers.NewsApplicationProvider"
        private val BASE_CONTENT_URI = Uri.parse("content://$CONTENT_AUTHORITY")
        val CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME)!!
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        private const val ALL_NEWS = 1
        private const val SPECIFIC_NEWS_TYPE = 2
        private const val SINGLE_NEWS = 3

        init {
            uriMatcher.addURI(CONTENT_AUTHORITY, TABLE_NAME, ALL_NEWS)
            uriMatcher.addURI(CONTENT_AUTHORITY, "$TABLE_NAME/#", SINGLE_NEWS)
            uriMatcher.addURI(CONTENT_AUTHORITY, "$TABLE_NAME/*", SPECIFIC_NEWS_TYPE)
        }
    }

    override fun onCreate(): Boolean {
        database = NewsApplicationDataBase(context!!)
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.e("Uri is", "$uri")
        return when (uriMatcher.match(uri)) {
            SPECIFIC_NEWS_TYPE -> { saveToDB(uri, values) }
            else -> { throw IllegalArgumentException("What the fuck is this? ") }
        }
     }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return when (uriMatcher.match(uri)) {
            SPECIFIC_NEWS_TYPE -> { fetchAllNewsOfSpecificType(uri.lastPathSegment) }
            else -> { throw IllegalArgumentException("What the fuck is this? ") }
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    private fun fetchAllNewsOfSpecificType(lastPathSegment: String?): Cursor? {
        var allNews: Cursor? = null
        lastPathSegment?.let{ newsType ->
            val db = database.readableDatabase
            allNews = db.query(NewsApplicationDataBase.TABLE_NAME,
                    NewsApplicationDataBase.TABLE_ROWS,
                    "${NewsApplicationDataBase.COLUMN_NEWS_TYPE} = ?",
                    arrayOf(newsType),
                    null, null, "" +
                    "${NewsApplicationDataBase.COLUMN_ID} ASC")
        }
        return allNews
    }

    private fun saveToDB(uri: Uri, values: ContentValues?): Uri? {
        val db = database.writableDatabase
        val row = db.insert(NewsApplicationDataBase.TABLE_NAME, null, values)
        if (row.toInt() == -1) return null
        return ContentUris.withAppendedId(uri, row)
    }

}
