package com.noblemajesty.newsapplication.database.models

import io.realm.RealmObject

open class FoodNews(
        var title: String? = null,
        var abstract: String? = null,
        var byline: String? = null,
        var publishedDate: String? = null,
        var image: String? = null
): RealmObject()