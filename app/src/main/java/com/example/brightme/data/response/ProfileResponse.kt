package com.example.brightme.data.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: DataProfile,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class DataProfile(

	@field:SerializedName("user")
	val user: User
)

data class User(

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("otp")
	val otp: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("userType")
	val userType: String,

	@field:SerializedName("isActive")
	val isActive: Boolean,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
