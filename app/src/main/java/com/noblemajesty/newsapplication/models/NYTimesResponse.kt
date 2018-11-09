package com.noblemajesty.newsapplication.models

import com.google.gson.Gson

data class NYTimesResponse(
        val status: String,
        val section: String,
        val num_results: Int,
        val results: List<Result>
) {
    fun toJson() = Gson().toJsonTree(this).asJsonObject
}