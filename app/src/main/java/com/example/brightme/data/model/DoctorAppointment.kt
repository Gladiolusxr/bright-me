package com.example.brightme.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorAppointment(
    val name: String,
    val number: String,
    val photo: Int
): Parcelable