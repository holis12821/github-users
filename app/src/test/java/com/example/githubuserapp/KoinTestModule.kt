package com.example.githubuserapp

import com.example.githubuserapp.di.networkModule
import com.example.githubuserapp.di.repositoryModule
import com.example.githubuserapp.di.useCaseModule
import com.example.githubuserapp.di.viewModelModule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinTestModule: KoinTest {
    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.DEBUG)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }.checkModules()
    }
}