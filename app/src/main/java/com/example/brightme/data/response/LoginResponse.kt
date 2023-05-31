package com.example.brightme.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String,
)

data class Data(

	@field:SerializedName("token")
	val token: String,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String,
)
