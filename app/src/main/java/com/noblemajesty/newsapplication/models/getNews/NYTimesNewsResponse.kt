package com.noblemajesty.newsapplication.models.getNews

import com.google.gson.Gson

data class NYTimesNewsResponse(
        val status: String,
        val section: String,
        val num_results: Int,
        val results: List<Result>
) {
    fun toJson() = Gson().toJsonTree(this).asJsonObject!!
}