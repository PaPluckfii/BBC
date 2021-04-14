package com.buildweek.bbc.view.activities.data.remote

import com.buildweek.bbc.view.activities.data.model.ResponseDTO
import retrofit2.Call
import retrofit2.http.GET


public interface APiClient {

    @GET("/getAll")
    fun getPost(): Call<List<ResponseDTO>>
}