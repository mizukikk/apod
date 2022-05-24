package com.example.apod.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    private var lastClickMillis = 0L
    val itemClickable
        get() :Boolean {
            val currentMillis = System.currentTimeMillis()
            val clickable = currentMillis - lastClickMillis > 300L
            if (clickable)
                lastClickMillis = currentMillis
            return clickable
        }
}