package com.buildweek.bbc.view.activities.ui.api

import com.buildweek.bbc.view.activities.ui.model.InShortsNews
import com.buildweek.bbc.view.activities.ui.model.LocalServerNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("news")
    fun getNews(
            @Query("category") category: String
    ): Call<InShortsNews>

    @GET("/bbc")
    fun getNewsByCategory(
        @Query("category") category: String
    ): Call<LocalServerNews>

    @GET("world")
    fun getNewsByCategory(

    ): Call<LocalServerNews>
}