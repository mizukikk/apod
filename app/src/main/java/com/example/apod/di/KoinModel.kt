package com.example.apod.di

import com.example.apod.BuildConfig
import com.example.apod.api.NetworkService
import com.example.apod.repository.ApodRepository
import com.example.apod.repository.IApodRepository
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module


val koinModule = module {
    single {
        if (BuildConfig.DEBUG)
            NetworkService(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        else
            NetworkService(null)
    }

    //repository
    single {
        val apodApi = get<NetworkService>()
        ApodRepository(apodApi.apodApi) as IApodRepository
    }
}
