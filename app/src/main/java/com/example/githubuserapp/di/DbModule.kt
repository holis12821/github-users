package com.example.githubuserapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.githubuserapp.data.local.db.LocalDb
import com.example.githubuserapp.data.local.db.dao.FavoriteDao
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { provideDatabase(androidApplication(), provideDatabaseName()) }
}

fun provideDatabaseName(): String = "FavoriteUserDB"

fun provideDatabase(app: Application, databaseName: String): LocalDb {
    return Room.databaseBuilder(app, LocalDb::class.java, databaseName).build()
}
