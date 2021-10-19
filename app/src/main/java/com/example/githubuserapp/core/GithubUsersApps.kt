/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.core

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.githubuserapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

/**
 * Level Application in android apps
 * in this class extended
 * @see MultiDexApplication : when your app and the libraries it references
 * exceed method 65,536 a build error will appear indicating that the app has reached the android build
 * architecture limit. to overcome this using
 * MultidexApplication.*/
class GithubUsersApps: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        mInstance = this
        MultiDex.install(this)
        Timber.plant(Timber.DebugTree())

        //Start koin with adding module from dependency
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@GithubUsersApps)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                preferencesModule,
                dbModule
            )
        }
    }

    companion object {
        private var mInstance: GithubUsersApps? = null
    }
}