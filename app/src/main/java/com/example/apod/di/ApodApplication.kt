package com.example.apod.di

import android.app.Application
import org.koin.core.context.startKoin

class ApodApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(koinModule)
        }
    }
}