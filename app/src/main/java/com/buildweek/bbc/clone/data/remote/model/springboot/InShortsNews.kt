package com.buildweek.bbc.clone.data.remote.model.springboot

data class InShortsNews(
    val category: String,
    val `data`: List<Data>,
    val success: Boolean
)