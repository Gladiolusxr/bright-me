package com.example.brightme.data.retrofit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data2(
    val productRecommendation: List<ProductRecommendation?>?,
    val publicUrl: String?,
    val skinDiseases: SkinDiseases?,
    val skinTypes: SkinTypes?
) : Parcelable