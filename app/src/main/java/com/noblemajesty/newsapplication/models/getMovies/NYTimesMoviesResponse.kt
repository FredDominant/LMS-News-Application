package com.noblemajesty.newsapplication.models.getMovies

data class NYTimesMoviesResponse(
        val status: String,
        val section: String,
        val num_results: Int,
        val results: List<Result>
)