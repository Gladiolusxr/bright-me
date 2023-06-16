package com.example.brightme.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Education(
    val title: String,
    val name: String,
    val video: String,
    val photo: Int
) : Parcelable
