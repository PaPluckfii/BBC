package com.buildweek.bbc.view.activities.ui.api

import com.buildweek.bbc.view.activities.ui.model.InShortsNews
import com.buildweek.bbc.view.activities.ui.model.LocalServerNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("region")
    fun getNewsByRegion(
        @Query("name") name: String
    ): Call<LocalServerNews>

    @GET("category")
    fun getNewsByCategory(
        @Query("category") category: String
    ): Call<LocalServerNews>

    @GET("world")
    fun getWorldNews(
    ): Call<LocalServerNews>

    @GET("tag")
    fun newsByTag(
        @Query(
            "q"
        ) q: String
    ): Call<LocalServerNews>

}