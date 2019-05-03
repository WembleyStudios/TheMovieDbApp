package com.wembleystudios.themoviedbapp

import android.app.Application
import com.wembleystudios.themoviedbapp.di.*
import org.koin.android.ext.android.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this,
            modules = listOf(appModule, presentationModule, domainModule, dataModule),
            extraProperties = mapOf(
                PROPERTY_API_KEY to BuildConfig.API_KEY,
                PROPERTY_BASE_URL to BuildConfig.BASE_URL
            )
        )
    }
}