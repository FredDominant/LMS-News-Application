package com.noblemajesty.newsapplication.models.getSports

data class NYTimesSportsResponse(
        val status: String,
        val section: String,
        val num_results: Int,
        val results: List<Result>
)