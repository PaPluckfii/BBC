package com.buildweek.bbc.clone.data.remote.api

import com.buildweek.bbc.clone.data.remote.model.opensourceapi.OpenSourceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenSourceApiService {

    @GET("v2/top-headlines")
    suspend fun getOpenSourceNews(
        @Query("country") country : String,
        @Query("category") category: String,
        @Query("sources") sources : String,
        @Query("q") keyword : String,
        @Query("apiKey") apiKey : String,
        @Query("pageSize") pageSize : Int,
        @Query("page") page : Int
    ) : Response<OpenSourceResponse>

}