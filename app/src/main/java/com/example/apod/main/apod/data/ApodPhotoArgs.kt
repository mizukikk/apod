package com.example.apod.main.apod.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApodPhotoArgs(
    val photoUrl: String
) : Parcelable