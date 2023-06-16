package com.example.brightme.data.retrofit

import com.example.brightme.data.response.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @FormUrlEncoded
    @POST("/auth/register")
    fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String,
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("/auth/verify")
    fun regisVerify(
        @Field("token") token: String,
        @Field("otp") otp: String,
    ): Call<RegisterVerificationResponse>

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
    fun forgotVerify(
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
    @POST("/predict")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Header("Authorization") token: String,
    ): Call<FileUpload2>

    @FormUrlEncoded
    @POST("/auth/googleOauth")
    fun googleLogin(
        @Field("credential") credential: String,
    ): Call<GoogleResponse>

    @GET("/product")
    fun getProduct(
    ): Call<ProductResponse>

    @GET("/profile")
    fun getProfile(
        @Header("Authorization") token: String,
    ): Call<ProfileResponse>
}