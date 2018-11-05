package com.noblemajesty.newsapplication.models.getFood

import com.google.gson.Gson

data class NYTimesFoodResponse(
        val status: String,
        val section: String,
        val num_results: Int,
        val results: List<Result>
) {
    fun toJson() = Gson().toJsonTree(this).asJsonObject
}