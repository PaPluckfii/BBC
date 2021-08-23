package com.buildweek.bbc.clone.repository

import com.buildweek.bbc.clone.data.remote.api.OpenSourceApiService
import com.buildweek.bbc.clone.data.remote.api.SpringBootApiService
import com.buildweek.bbc.clone.data.remote.model.opensourceapi.OpenSourceResponse
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val springBootApiService: SpringBootApiService,
    private val openSourceApiService: OpenSourceApiService
) {

    suspend fun getNewsFromSpringBoot(
        type : String,
        keyword: String
    ){

    }

    suspend fun getNewsFromOpenApi(
        country : String,
        category : String,
        source : String,
        keyword : String,
        apiKey : String
    ) : Response<OpenSourceResponse>{
        return openSourceApiService.getOpenSourceNews(
            country,
            category,
            source,
            keyword,
            apiKey,
            10,
            1
        )
    }

}