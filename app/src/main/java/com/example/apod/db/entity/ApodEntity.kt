package com.example.apod.db.entity

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apod.R
import com.example.apod.api.obj.Apod
import com.example.apod.di.ApodApplication
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "apod")
data class ApodEntity(
    val apodSite: String,
    val copyright: String,
    val date: String,
    val description: String,
    val hdUrl: String,
    val mediaType: String,
    val title: String,
    val url: String,
    var favorite: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val _id: Int? = null,
) : Parcelable {
    constructor(apod: Apod) : this(
        apod.apodSite,
        apod.copyright,
        apod.date,
        apod.description,
        apod.hdurl,
        apod.mediaType,
        apod.title,
        apod.url
    )

    val favoriteColorFilter
        get() = if (favorite) {
            PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        } else {
            PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP)
        }
}