package com.example.apod.api.service

import com.example.apod.api.obj.Apod
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApodService {
    @GET("apod.json")
    fun fetchApodList(): Deferred<Response<List<Apod>>>
}