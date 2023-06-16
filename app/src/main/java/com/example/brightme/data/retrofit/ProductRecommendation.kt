package com.example.brightme.data.retrofit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductRecommendation(
    val category: Category?,
    val description: String?,
    val id: Int?,
    val imageUrl: String?,
    val name: String?,
    val price: Int?,
    val tag: String?
) : Parcelable