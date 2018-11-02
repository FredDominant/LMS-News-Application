package com.noblemajesty.newsapplication.models.getFood

data class NYTimesFoodResponse(
        val status: String,
        val section: String,
        val num_results: Int,
        val results: List<Result>
)