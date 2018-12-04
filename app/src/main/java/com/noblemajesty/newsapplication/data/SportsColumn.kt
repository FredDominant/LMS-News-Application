package com.noblemajesty.newsapplication.data

import android.arch.persistence.room.Entity
import com.noblemajesty.newsapplication.models.Multimedia

@Entity(tableName = "sports")
data class SportsColumn(
        val title: String,
        val abstract: String,
        val url: String,
        val byline: String,
        val publishedDate: String,
        val multimedia: List<Multimedia>
)