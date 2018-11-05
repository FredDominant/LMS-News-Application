package com.noblemajesty.newsapplication.models.getSports

import com.google.gson.Gson

data class NYTimesSportsResponse(
        val status: String,
        val section: String,
        val num_results: Int,
        val results: List<Result>
) {
    fun toJson() = Gson().toJsonTree(this).asJsonObject
}