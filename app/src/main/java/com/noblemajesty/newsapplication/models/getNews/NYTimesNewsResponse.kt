package com.noblemajesty.newsapplication.models.getNews

data class NYTimesNewsResponse(
        val status: String,
        val section: String,
        val num_results: Int,
        val results: List<Result>
)