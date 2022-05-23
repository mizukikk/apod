package com.example.apod.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apod.api.obj.Apod

@Entity(tableName = "apod")
data class ApodEntity(
    val apod_site: String,
    val copyright: String,
    val date: String,
    val description: String,
    val hdurl: String,
    val media_type: String,
    val title: String,
    val url: String,
    @PrimaryKey(autoGenerate = true)
    val _id: Int? = null,
) {
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
}