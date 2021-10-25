package com.example.githubuserapp

import androidx.test.core.app.ApplicationProvider
import com.example.githubuserapp.di.*
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinTestModule: KoinTest {
    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.DEBUG)
            androidContext(ApplicationProvider.getApplicationContext())
            modules(
                listOf(
                    dbModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    preferencesModule
                )
            )
        }.checkModules()
    }
}