package com.buildweek.bbc.view.activities.ui.api

import com.buildweek.bbc.view.activities.ui.model.InShortsNews
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    const val BASE_URL = "https://4bec25a678f2.ngrok.io/api/bbc/"
    val newsInstance: ApiService
    init {
        val retrofitBuilder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        newsInstance = retrofitBuilder.create(ApiService::class.java)
    }
}