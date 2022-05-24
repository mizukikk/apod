package com.example.apod.di

import android.app.Application
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import org.koin.core.context.startKoin

class ApodApplication : Application() {

    companion object {
        private var INSTANCE: Application? = null
        val appContext get() = INSTANCE!!.applicationContext
        fun getString(@StringRes id: Int) = appContext.getString(id)
        fun getColor(@ColorRes id: Int) = ContextCompat.getColor(appContext, id)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        startKoin {
            modules(koinModule)
        }
    }
}