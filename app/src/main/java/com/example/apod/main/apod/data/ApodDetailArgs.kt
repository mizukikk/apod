package com.example.apod.main.apod.data

import android.os.Parcelable
import com.example.apod.db.entity.ApodEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApodDetailArgs(
    val apodEntity: ApodEntity
) : Parcelable