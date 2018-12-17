package com.noblemajesty.newsapplication.application

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class NewsApplication : Application() {

    companion object {
        const val DATABASE_NAME = "newsApplicationDatabase.realm"
    }

    override fun onCreate() {
        super.onCreate()
        initializeRealm(this)
    }

    private fun initializeRealm(context: Context) {
        Realm.init(context)
        val realmConfiguration = RealmConfiguration.Builder().apply { name(DATABASE_NAME) }
        Realm.setDefaultConfiguration(realmConfiguration.build())
    }
}