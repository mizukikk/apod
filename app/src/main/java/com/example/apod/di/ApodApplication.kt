package com.example.apod.di

import android.app.Application
import org.koin.core.context.startKoin

class ApodApplication:Application() {

    companion object{
        private var INSTANCE: Application? = null
        val appContext get() = INSTANCE!!.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        startKoin {
            modules(koinModule)
        }
    }
}