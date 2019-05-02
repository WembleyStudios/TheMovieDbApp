package com.wembleystudios.themoviedbapp

import android.app.Application
import com.wembleystudios.themoviedbapp.di.appModule
import com.wembleystudios.themoviedbapp.di.dataModule
import com.wembleystudios.themoviedbapp.di.domainModule
import com.wembleystudios.themoviedbapp.di.presentationModule
import org.koin.android.ext.android.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules = listOf(appModule, presentationModule, domainModule, dataModule))
    }
}