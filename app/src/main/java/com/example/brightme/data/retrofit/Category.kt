package com.example.brightme.data.retrofit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int?,
    val name: String?
) : Parcelable