package com.noblemajesty.newsapplication.models.getMovies

import com.google.gson.Gson

data class NYTimesMoviesResponse(
        val status: String,
        val section: String,
        val num_results: Int,
        val results: List<Result>
) {
    fun toJson() = Gson().toJsonTree(this).isJsonObject
}