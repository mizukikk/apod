package com.example.apod.extension

import com.example.apod.di.ApodApplication


fun Float.dp2Px(): Float {
    val density = ApodApplication.appContext.resources.displayMetrics.density
    return this * density
}