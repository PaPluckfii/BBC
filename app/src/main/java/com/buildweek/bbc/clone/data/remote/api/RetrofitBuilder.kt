package com.buildweek.bbc.clone.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    const val BASE_URL = "https://532629708222.ngrok.io/api/bbc/"

    val NEWS_INSTANCE: SpringBootApiService
    init {
        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        NEWS_INSTANCE = retrofitBuilder.create(SpringBootApiService::class.java)
    }
}