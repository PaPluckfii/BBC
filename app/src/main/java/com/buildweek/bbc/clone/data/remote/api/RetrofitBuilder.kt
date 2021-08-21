package com.buildweek.bbc.clone.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    const val BASE_URL = "https://532629708222.ngrok.io/api/bbc/"

    val newsInstance: ApiService
    init {
        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        newsInstance = retrofitBuilder.create(ApiService::class.java)
    }
}