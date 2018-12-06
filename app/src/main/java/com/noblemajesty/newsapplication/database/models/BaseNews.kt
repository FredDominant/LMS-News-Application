package com.noblemajesty.newsapplication.database.models

interface BaseNews {
    fun getTitle(): String?
    fun getAbstract(): String?
    fun getByline(): String?
    fun getPublishedDate(): String?
    fun getImage(): String?
}