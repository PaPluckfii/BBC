package com.buildweek.bbc.view.activities.ui.model

import com.buildweek.bbc.view.activities.ui.api.ApiService

data class InShortsNews(
    val category: String,
    val `data`: List<Data>,
    val success: Boolean
)