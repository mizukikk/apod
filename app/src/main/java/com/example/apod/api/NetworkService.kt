package com.example.apod.api

import com.example.apod.api.service.ApodService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkService(interceptor: Interceptor?) {
    companion object {
        private const val APOD_BASE_URL = "https://raw.githubusercontent.com/robert0ng/NasaDataSet/main/"
        private const val WRITE_TIME_OUT = 30L
        private const val READ_TIME_OUT = 30L
        private const val CONNECT_TIME_OUT = 30L
    }

    val apodApi: ApodService

    init {
        val clientBuilder = OkHttpClient.Builder()
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        if (interceptor != null) {
            clientBuilder.addInterceptor(interceptor)
        }

        val client = clientBuilder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(APOD_BASE_URL)
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apodApi = retrofit.create(ApodService::class.java)
    }
}