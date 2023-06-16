package com.example.brightme.data.response

import com.google.gson.annotations.SerializedName

data class VerificationResponse(

    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,
)