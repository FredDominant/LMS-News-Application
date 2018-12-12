package com.noblemajesty.newsapplication.providers

import android.arch.persistence.room.Room
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.noblemajesty.newsapplication.database.NewsApplicationDAO
import com.noblemajesty.newsapplication.database.NewsApplicationDataBase
import com.noblemajesty.newsapplication.utils.Constants.DB_NAME

class NewsAppplicationProvider: ContentProvider() {

    companion object {
        const val CONTENT_AUTHORITY = "com.noblemajesty.newsapplication.providers.NewsAppplicationProvider"
        val BASE_CONTENT_URI = Uri.parse("content://$CONTENT_AUTHORITY")
        val CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, DB_NAME)
        // content://com.noblemajesty.newsapplication.providers.NewsApplicationProvider/newsApplicationDatabase
    }

    private lateinit var newsApplicationDataBase: NewsApplicationDataBase
    private var newsApplicationDAO: NewsApplicationDAO? = null


    override fun onCreate(): Boolean {
        newsApplicationDataBase = Room.databaseBuilder(context,
                NewsApplicationDataBase::class.java,
                DB_NAME).build()

        newsApplicationDAO = newsApplicationDataBase.getDao()
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
     }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return null
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
}