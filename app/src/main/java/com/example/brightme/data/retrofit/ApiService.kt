package com.example.brightme.data.retrofit

import com.example.brightme.data.response.*
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

data class FileUploadResponse(
    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("message")
    val message: String,
)

data class Data(
    @field:SerializedName("imageUrl")
    val imageUrl: String
)

interface ApiService {

    @FormUrlEncoded
    @POST("/auth/register")
    fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String,
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("/auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("/auth/forgotPassword")
    fun forgotPassword(
        @Field("email") email: String,
    ): Call<ForgotPasswordResponse>

    @FormUrlEncoded
    @POST("/auth/forgotVerify")
    fun verify(
        @Field("token") token: String,
        @Field("otp") otp: String,
    ): Call<VerificationResponse>

    @FormUrlEncoded
    @POST("/auth/resetPassword")
    fun resetPassword(
        @Field("token") token: String,
        @Field("newPassword") newPassword: String,
        @Field("confirmPassword") confirmPassword: String
    ): Call<ResetPasswordResponse>

    @Multipart
    @POST("/upload")
    fun uploadImage(
        @Part file: MultipartBody.Part,
    ): Call<FileUploadResponse>

    @FormUrlEncoded
    @POST("/auth/googleOauth")
    fun googleLogin(
        @Field("credential") credential: String,
    ): Call<GoogleResponse>
}