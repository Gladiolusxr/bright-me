package com.example.brightme.data.retrofit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FileUpload2(
    val code: Int?,
    val `data`: Data2?,
    val success: Boolean?
) : Parcelable