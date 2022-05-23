package com.example.apod.api.obj
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Apod(
    @SerializedName("apod_site")
    @Expose
    val apodSite: String,
    @SerializedName("copyright")
    @Expose
    val copyright: String,
    @SerializedName("date")
    @Expose
    val date: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("hdurl")
    @Expose
    val hdurl: String,
    @SerializedName("media_type")
    @Expose
    val mediaType: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("url")
    @Expose
    val url: String
)