package com.example.brightme.data.retrofit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SkinDiseases(
    val accuracy: Double?,
    val label: String?,
    val predictions: List<Double?>?
) : Parcelable