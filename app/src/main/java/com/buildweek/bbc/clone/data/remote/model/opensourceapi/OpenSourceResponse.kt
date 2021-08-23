package com.buildweek.bbc.clone.data.remote.model.opensourceapi


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OpenSourceResponse(
    @SerializedName("articles")
    val articles: MutableList<Article>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
) : Serializable