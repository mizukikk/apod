package com.example.apod.di

import androidx.room.Room
import com.example.apod.BuildConfig
import com.example.apod.api.NetworkService
import com.example.apod.db.ApodDatabase
import com.example.apod.repository.ApodRepository
import com.example.apod.repository.IApodRepository
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module


val koinModule = module {
    //api
    single {
        if (BuildConfig.DEBUG)
            NetworkService(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        else
            NetworkService(null)
    }

    //data base
    single {
        Room.databaseBuilder(
            ApodApplication.appContext,
            ApodDatabase::class.java,
            ApodDatabase.MLTD_DB_NAME
        ).build()
    }

    //repository
    single {
        val apodApi = get<NetworkService>()
        val apodDatabase = get<ApodDatabase>()
        ApodRepository(apodApi.apodApi, apodDatabase.apodDao()) as IApodRepository
    }
}
