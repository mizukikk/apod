package com.example.apod.main.apod.data

import android.os.Parcelable
import com.example.apod.main.apod.adapter.ApodPageAdapter
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApodListArgs(
    val type: ApodPageAdapter.Type
) : Parcelable