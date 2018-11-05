package com.noblemajesty.newsapplication.utils

import com.google.gson.Gson

object ClassConverter {
    fun <T> convertToJson(classToBeConverted: Class<T>) = Gson().toJson(classToBeConverted)
}