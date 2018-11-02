package com.noblemajesty.newsapplication.models.getNews

data class Result(
        val section: String,
        val subsection: String,
        val title: String,
        val abstract: String,
        val url: String,
        val byline: String,
        val item_type: String,
        val updated_date: String,
        val created_date: String,
        val published_date: String,
        val material_type_facet: String,
        val kicker: String,
        val des_facet: List<String>,
        val org_facet: List<String>,
        val per_facet: List<String>,
        val geo_facet: List<Any>,
        val multimedia: List<Multimedia>,
        val short_url: String
)