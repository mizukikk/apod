package com.example.apod.extension

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.request.ImageRequest
import com.example.apod.R


fun ImageRequest.Builder.setProgress(context: Context) {
    CircularProgressDrawable(context).apply {
        strokeWidth = 5f.dp2Px()
        centerRadius = 30f.dp2Px()
        setColorSchemeColors(ContextCompat.getColor(context, R.color.purple_200))
        start()
    }
}