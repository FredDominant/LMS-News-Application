package com.noblemajesty.newsapplication.database.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FoodNews(
        @PrimaryKey
        var id: Int? = 0,
        var title: String? = null,
        var abstract: String? = null,
        var byline: String? = null,
        var publishedDate: String? = null,
        var image: String? = null
): RealmObject()