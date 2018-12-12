package com.noblemajesty.newsapplication.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
open class News(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var title: String? = null,
        var abstract: String? = null,
        var byline: String? = null,
        var publishedDate: String? = null,
        var image: String? = null,
        var newsType: String? = null
)
