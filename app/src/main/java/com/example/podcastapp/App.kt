package com.example.podcastapp

import android.app.Application
import com.example.data.di.repositoryModule
import com.example.core.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@App)
            // Load modules
            modules(
                apiModule,
                repositoryModule
            )
        }
    }
}