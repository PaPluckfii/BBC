package com.buildweek.bbc.view.activities.data.model


import com.google.gson.annotations.SerializedName
data class ResponseDTO(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("username")
	val username: String? = null
)